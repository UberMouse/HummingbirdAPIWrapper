import nz.ubermouse.hummingbird._
import nz.ubermouse.hummingbird.AnimeResult
import nz.ubermouse.hummingbird.MangaResult

class HummingbirdHttpInteractionsSpec extends UnitSpec {

  "#search" - {
    "works" - {
      val interactions = new HummingbirdHttpInteractions
      val expected = List(
        AnimeResult("Kanon","http://static.hummingbird.me/anime/poster_images/000/007/659/large/7659.jpg?1383501823","/anime/kanon-f71d1c5e-4eea-413f-9b9b-3ae2b3a91caf"),
        AnimeResult("Photokano", "http://static.hummingbird.me/anime/poster_images/000/007/421/large/7421.jpeg?1383501184", "/anime/photokano"),
        AnimeResult("Karas", "http://static.hummingbird.me/anime/poster_images/000/000/356/large/356.jpg?1383476844", "/anime/karas"),
        MangaResult("Kare Kano Hajimemashita", "http://static.hummingbird.me/manga/poster_images/000/014/304/large/55369.jpg?1388665872", "/manga/kare-kano-hajimemashita"),
        MangaResult("Kareshi Kanojo no Jijou", "http://static.hummingbird.me/manga/poster_images/000/000/050/large/12187.jpg?1388288412", "/manga/kareshi-kanojo-no-jijou")
      )

      val results = interactions.search("Kare Kano")


      results should be (expected)
    }
  }

  "#lookup" - {
     "By slug" - {
       "works" - {
         val interactions = new HummingbirdHttpInteractions
         val expected = Anime(7881,
                              "noragami",
                              "Noragami",
                              None,
                              "Noragami",
                              Some("Yato is a minor god whose dream is to have followers worshipping him. Unfortunately, his dream is far from coming true since he doesn't even have a single shrine dedicated to him. He later stumbles upon Iki Hiyori, and saves her life..."),
                              "http://static.hummingbird.me/anime/poster_images/000/007/881/large/noragami_1.jpg?1389205049",
                              List("Action", "Adventure", "Supernatural"),
                              "TV",
                              "2014-01-05",
                              Some("2014-03-23"),
                              List("http://static.hummingbird.me/gallery_images/images/000/004/931/thumb/qbypkko.jpg?1392746242",
                                "http://static.hummingbird.me/gallery_images/images/000/004/932/thumb/8iiS23M.jpg?1392746253",
                                "http://static.hummingbird.me/gallery_images/images/000/004/933/thumb/QG2xVN7.jpg?1392746263",
                                "http://static.hummingbird.me/gallery_images/images/000/004/934/thumb/Noragami-anime-002.jpg?1392746305"),
                              Some("Bo4T6wfylxM"),
                              4.08744522849705,
                              "PG13",
                              12,
                              24)

         val entry = interactions.lookup("noragami")

         entry should be (expected)
       }
     }
  }
}
