package nz.ubermouse.hummingbird

import scala.util.{Success, Failure}
import scala.concurrent.duration._
import akka.actor.ActorSystem
import akka.pattern.ask
import akka.event.Logging
import akka.io.IO
import spray.json.{JsonFormat, DefaultJsonProtocol}
import spray.can.Http
import spray.httpx.SprayJsonSupport
import SprayJsonSupport._
import spray.client.pipelining._
import spray.util._
import HummingbirdProtocol._
import java.net.URLEncoder
import scala.concurrent.Await
import akka.util.Timeout

class HummingbirdHttpInteractions extends HttpInteractions {
  implicit val system = ActorSystem("simple-spray-client")
  import system.dispatcher // execution context for futures below

  def search(title: String): Iterable[SearchResult] = {
    val cleanTitle = URLEncoder.encode(title, "UTF-8")
    val pipeline = sendReceive ~> unmarshal[SearchResults]

    val responseFuture = pipeline {
      Get(s"http://hummingbird.me/search.json?query=$cleanTitle&type=mixed")
    }

    val results = Await.result(responseFuture, 5 seconds)
    results.search
  }

  def lookup(slug: String): HummingbirdEntry = findBySlugOrId(slug)

  def lookup(id: Int): HummingbirdEntry = findBySlugOrId(id.toString)

  private def findBySlugOrId(identifier: String): HummingbirdEntry = {
    val pipeline = sendReceive ~> unmarshal[Anime]

    val responseFuture = pipeline {
      Get(s"http://hummingbird.me/api/v2/anime/$identifier")
    }

    Await.result(responseFuture, 5 seconds)
  }
}
