package nz.ubermouse.hummingbird

class Hummingbird(http: HttpInteractions) {
  def search(name: String): Iterable[HummingbirdEntry] = {
    val searchResults = http.search(name)
    searchResults.flatMap {
      case result: AnimeResult => Some(result)
      case _ => None
    }.map(result => http.lookup(result.slug))
  }
}
