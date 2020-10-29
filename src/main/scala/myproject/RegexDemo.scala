package myproject

object RegexDemo extends Demo {

  override def run(): Unit = {

    // match
    {
      val text1 = "Hello-somebody-123"
      val text2 = "Hello somebody!"
      val rx = """^[a-zA-Z0-9\-]+$""".r

      // by FindPrefix
      pf("text1: %s %s ", text1, rx.findPrefixMatchOf(text1) != None)
      pf("text2: %s %s ", text1, rx.findPrefixMatchOf(text2) != None)

      // by pattern matcher
      pf("Str1 match: %s %s", text1, rx.pattern.matcher(text1).matches)
      pf("Str2 match: %s %s", text2, rx.pattern.matcher(text2).matches)

      // by String.matches
      pf("Str1 match: %s %s", text1, text1.matches("""^[a-zA-Z0-9\-]+$"""))
      pf("Str2 match: %s %s", text2, text2.matches("""^[a-zA-Z0-9\-]+$"""))
    }

    // find One
    {
      val text = "HELLO 1111 $$$ PRETTY 453 Yahhoooo!! 12312 gfdre RX VALUE ! ## :)"
      val rx = raw"[A-Z][a-z]{3,}\W".r
      pf("Found first: %s", rx.findFirstIn(text).head)
    }

    // findAll
    {
      val text = "HELLO 1111 $$$ PRETTY 453 asd!! 12312 gfdre RX WORDS ! ## :)"
      val rx = "[A-Z]{3,}".r
      val found = rx.findAllIn(text).toList.mkString(" ")
      ps(s"find all: $found")
    }

    // groups
    {
      val xml =
        """
          |<users>
          |<user name="Vasya"></user>
          |<user name="Kolya"></user>
          |<admin name="Olya"></admin>
          |<user noname></user>
          |</users>
        """.stripMargin

      val rxUser = """<([a-z]+)\s+name\s*=\s*"([^"]+)"""".r
      for(rxUser(tag, name) <- rxUser.findAllIn(xml)){
        ps(s" -- $tag: $name")
      }
    }

    // replace one
    {
      val text = "Welcome to party on December 15!"
      val rx = "December|January|February".r

      val fixed = rx.replaceFirstIn(text, "June")
      ps(s"Fixed invite: $fixed")
    }

    // replace all
    {
      val text = "[header:Welcome][text:Hello here Vasya!]"
      val rxTag = "header|text".r
      val tagText = rxTag.replaceAllIn(text, "div")
      ps(s"tagT: $tagText")

      val tg = "([^\\[\\]]+)" // text group
      val rxFormat = s"\\[${tg}:${tg}\\]".r
      ps(s"Fmt: ${rxFormat.regex}")
      val html = rxFormat.replaceAllIn(tagText, m => s"<${m.group(1)}>${m.group(2)}<${m.group(1)}>\n")
      ps(s"html:\n$html")
    }

    // split
    val text = "aaa-bbb_ccc ddd=eee,fff.ggg%hhh s*s^s"
    val rx = "[^\\w\\*\\^]".r
    val words = rx.split(text).toList
    ps(s"split words: $words")

  }
}
