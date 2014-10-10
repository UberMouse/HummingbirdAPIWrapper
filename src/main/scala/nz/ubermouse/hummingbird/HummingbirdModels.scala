package nz.ubermouse.hummingbird

sealed abstract class HummingbirdEntry
case class Anime(id: Int,
                 slug: String,
                 canonical_title: String,
                 english_title: Option[String],
                 romaji_title: String,
                 synopsis: Option[String],
                 poster_image: String,
                 genres: List[String],
                 `type`: String,
                 started_airing: String,
                 finished_airing: Option[String],
                 screencaps: List[String],
                 youtube_trailer_id: Option[String],
                 community_rating: Double,
                 age_rating: Option[String],
                 episode_count: Int,
                 episode_length: Int) extends HummingbirdEntry
case class Manga() extends HummingbirdEntry

sealed abstract class SearchResult(title: String, image: String, link: String) {
  def slug = link.split("/").last
}
case class AnimeResult(title: String, image: String, link: String) extends SearchResult(title, image, link)
case class MangaResult(title: String, image: String, link: String) extends SearchResult(title, image, link)
case class UserResult(title: String, image: String, link: String) extends SearchResult(title, image, link)
case class SearchResults(search: List[SearchResult])