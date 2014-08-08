package nz.ubermouse.hummingbird

sealed abstract class HummingbirdEntry
case class HummingbirdAnime(name: String, id: Int) extends HummingbirdEntry
case class HummingbirdManga() extends HummingbirdEntry