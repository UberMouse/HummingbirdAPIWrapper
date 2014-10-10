package nz.ubermouse.hummingbird

import spray.json._
import DefaultJsonProtocol._

object HummingbirdProtocol extends DefaultJsonProtocol {
  implicit object SearchProtocol extends RootJsonFormat[SearchResult] {
    def write(obj: SearchResult): JsValue = throw new NotImplementedError("Why are you using this")

    //This is gross
    def read(json: JsValue): SearchResult = {
      json match {
        case o: JsObject => {
          val typ = o.fields("type").convertTo[String]
          val title = o.fields("title").convertTo[String]
          val image = o.fields("image").convertTo[String]
          val link = o.fields("link").convertTo[String]
          typ match {
            case x if x == "anime" => AnimeResult(title, image, link)
            case x if x == "manga" => MangaResult(title, image, link)
            case x if x == "user" => UserResult(title, image, link)
            case invalid => throw new Exception(s"invalid search result type: $invalid")
          }
        }
        case _ => throw new Exception("json was not a JsObject")
      }
    }
  }

  implicit val animeFormat = jsonFormat17(Anime)

  implicit val animeResultFormat = jsonFormat3(AnimeResult)
  implicit val mangaResultFormat = jsonFormat3(MangaResult)
  implicit val userResultFormat = jsonFormat3(UserResult)
  implicit val searchResultsFormat = jsonFormat1(SearchResults)
}
