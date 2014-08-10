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
import scala.concurrent.{Future, Await}
import akka.util.Timeout

class HummingbirdHttpInteractions extends HttpInteractions {
  implicit val system = ActorSystem("simple-spray-client")
  import system.dispatcher // execution context for futures below

  def search(title: String): Future[Iterable[SearchResult]] = {
    val cleanTitle = URLEncoder.encode(title, "UTF-8")
    val pipeline = sendReceive ~> unmarshal[SearchResults]

    pipeline {
      Get(s"http://hummingbird.me/search.json?query=$cleanTitle&type=mixed")
    }.map(_.search)
  }

  def lookup(slug: String): Future[HummingbirdEntry] = findBySlugOrId(slug)

  def lookup(id: Int): Future[HummingbirdEntry] = findBySlugOrId(id.toString)

  private def findBySlugOrId(identifier: String): Future[HummingbirdEntry] = {
    val pipeline = sendReceive ~> unmarshal[Anime]
    pipeline {
      Get(s"http://hummingbird.me/api/v2/anime/$identifier")
    }
  }
}
