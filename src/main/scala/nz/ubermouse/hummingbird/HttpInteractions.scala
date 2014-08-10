package nz.ubermouse.hummingbird

trait HttpInteractions {
  def search(title: String): Iterable[SearchResult]
  def lookup(slug: String): HummingbirdEntry
  def lookup(id: Int): HummingbirdEntry
}
