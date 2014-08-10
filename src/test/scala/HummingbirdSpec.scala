import nz.ubermouse.hummingbird.{HummingbirdModule, Anime, Hummingbird}
import scaldi.{Injectable, Module}

class HummingbirdSpec extends UnitSpec {

  implicit val module = new Module {
    bind[Hummingbird] to injected[Hummingbird]
  } :: new HummingbirdModule

  "Describe Hummingbird API Wrapper" - {
    "It provides searching functionality" - {
      "If I perform a search I get a list of results back" - {
        val hummingbird = inject[Hummingbird]

        val results = hummingbird.search("Gekkan Shojo Noziaki Kun")

        results.size should be (3)
      }
    }
  }
}
