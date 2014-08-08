import nz.ubermouse.hummingbird.{HummingbirdAnime, Hummingbird}
import scaldi.{Injectable, Module}

class TestModule extends Module {

}

class HummingbirdSpec extends UnitSpec with Injectable {

  implicit val module = new TestModule

  "Describe Hummingbird API Wrapper" - {
    "It provides searching functionality" - {
      "If I perform a search I get a list of results back" - {
        val hummingbird = inject[Hummingbird]

        val results = hummingbird.search("Gekkan Shojo Noziaki Kun")

        results should be (List(HummingbirdAnime(name = "Gekkan Shojo Nozaki Kun", id = 1221)))
      }
    }
  }
}
