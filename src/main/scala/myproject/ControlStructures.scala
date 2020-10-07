package myproject

object ControlStructures extends Demo {
  override def run(): Unit = {

    // for by Sequence
    val loopNums = Array(1, 2, 3, 4, 5, 6, 7, 8, 9)
    for(x <- loopNums){
      pt(x)
      pt(" ")
    }
    ps

    // for by number function
    // 1.until(10)
    for(x <- 1 until 10){
      pt(x)
      pt(" ")
    }
    ps

    // while

    var count = 0
    ps("While counter:")
    pt("\t")
    while(count < 10){
      pt(count)
      pt(", ")
      count += 1
    }
    ps

    // while: iterator
    val whileNums = List(11, 22, 33, 44, 55, 66, 77)
    ps("While iterator:")
    pt("\t")
    val whIter: Iterator[Int] = whileNums.iterator

    while(whIter.hasNext){
      pt(whIter.next)
      pt(" ")
    }
    ps

    // while: range and iterator
    val untilIter = (-5 until 6).iterator
    ps("While range iterator")
    while(untilIter.hasNext){
      pt(untilIter.next)
      pt("; ")
    }


    // `if` statement.
    // (note: any block returns value)
    var oddNums = List[Int]()
    for(x <- 1 until 10){
      if(x % 2 > 0){
        oddNums = x :: oddNums
      } else {
        oddNums = 1000 + x :: oddNums
      }
    }
    pf("oddNums: %s", oddNums.reverse)

    // one line: if(cond) value1 else value2

    val valX = 123
    val valY = if(valX % 2 > 0) valX else valX + 1
    pf("odd 123: %d", valY)

    // block result assignment
    val acronym = {val words = Array("Hit", "editor", "lorem", "look", "one");
      var res = List[Char]()
      for(c: String <- words){ res = c.head :: res };
      res.reverse.mkString("")
    }
    pf("Acro: %s", acronym)

    // for with if
    ps("for - if")
    for(x <- 1 until 10 if(x % 3 == 0)){
      pt(x); pt(" ")
    }
    ps

    // for-generator


    // switch-case

    val matchNums = List(-5, 0, 1, 2, 3, 10)

    pf("-- Matching nums --", matchNums)
    for(num <- matchNums) {
      // matching num with cases
      num match
      {
        // Simple cases
        case 1 => pf("one: %d", num)
        case 2 => pf("two: %d", num)

        // Case with condition
        case n if n > 2 => pf("2 more: %d", num)
        case n if n < 0 => pf("zero less: %d", num)

        // default case
        case _ => pf("other: %d", num)
      }
    }
  }
}
