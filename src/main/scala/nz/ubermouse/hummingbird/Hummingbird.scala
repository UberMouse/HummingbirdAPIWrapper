package nz.ubermouse.hummingbird

import scala.concurrent.{Future, Await}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

class Hummingbird(http: HttpInteractions) {
  def search(name: String): Iterable[HummingbirdEntry] = {
    val searchResults = Await.result(http.search(name), 10 seconds)
    val lookup = (result: SearchResult) => http.lookup(result.slug)
    val animeResults = searchResults.flatMap {
      case result: AnimeResult => Some(result)
      case _ => None
    }
    val entries = Future.traverse(animeResults)(lookup)
    Await.result(entries, 10 seconds)
  }
}
