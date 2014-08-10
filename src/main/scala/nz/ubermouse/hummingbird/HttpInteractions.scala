package nz.ubermouse.hummingbird

import scala.concurrent.Future

trait HttpInteractions {
  def search(title: String): Future[Iterable[SearchResult]]
  def lookup(slug: String): Future[HummingbirdEntry]
  def lookup(id: Int): Future[HummingbirdEntry]
}
