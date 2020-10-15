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
    // 1.to(10)
    ps(" for to")
    for(x <- 1 to 10){
      pt(x)
      pt(" ")
    }
    ps

    // for by number function
    // 1.until(10)
    ps("for until")
    for(x <- 1 until 10){
      pt(x)
      pt(" ")
    }
    ps

    // while
    // by counter
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
    ps


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
      for(w: String <- words){ res = w.head :: res };
      res.reverse.mkString("")
    }
    pf("Acronym: %s", acronym)

    // for with if
    ps("for - if")
    for(x <- 1 until 10 if(x % 3 == 0)){
      pt(x); pt(" ")
    }
    ps

    // complicated for
    // for(x <- seq1; y <- seq2 if condition )
    ps("Complicated `for`")
    for(
      x <- 1 to 100;
      y <- List(11, 13, 17, 19)
      if x > 40 && x % y == 0
    ) {
      pt("%d(%d,%d) ".format(x, y, x / y));
    }
    ps

    // for generator:
    ps("Generator")

    val numSeq5 = 1 to 5
    val numGen5 = for(x <- numSeq5) yield x * 5 // 5 - 25
    ps(numGen5)

    // yield with block
    val numVec17 = for(x <- 1 to 17) yield {val y = x * 2; y * 5}
    ps(numVec17 toList)

    // type of generator result
    val charList = for(x <- 0 to 10) yield "%c".format('A' + x)
    pf("CharList: %s | %s", charList.getClass.getName, charList.mkString(", "))


    // Break loop

    import scala.util.control.Breaks._

    // break example
    ps("break 1-10 on 5")
    breakable{
      for(x <- 1 to 10){
        if(x == 5){
          break
        }
        pt(x); pt(" ")
      }
    }
    ps

    // continue-like example
    ps("continue on %3==0 1-10 ")
    for(x <- 1 to 10){
      breakable{
        if(x % 3 == 0){
          break
        }
        pt(x); pt(" ")
      }
    }
    ps


    // switch-case

    val matchNums = List(-5, 0, 1, 2, 3, 10, 25)

    pf("-- Matching nums --", matchNums)
    for(num <- matchNums) {
      // matching num with cases
      num match
      {
        // Simple cases
        case 1 => pf("one: %d", num)
        case 2 => pf("two: %d", num)

        // Case with condition
        case n if n > 5 => pf("2 more: n = %d", n)
        case n if n < 0 => pf("lt 0: n = %d", n)

        // default case
        case _ => pf("other: %d", num)
      }
    }

    // Try-catch
    for(x <- List(0, 1, 321)){
      try{
        ExceptionMaker.go(x)
      } catch {

        case ex: CustomExceptionOne => {
          pf("Except case 1: %s", ex.getMessage)
        }

        case ex: CustomException002 => pf(s"Error case: ${ex.getMessage}")

        case _: Throwable => ps("Default exception caught")

      } finally {
        ps(s"### Finally block of iter: $x")
      }
    }


  }

}


class CustomExceptionOne(msg: String) extends Exception(msg){

}

class CustomException002(msg: String) extends Exception(msg){

}

object ExceptionMaker {
  def go(arg: Int=0) = {
    if (arg == 0){
      throw new Exception("Default test exception")
    }
    arg match {
      case x if x > 0 && x < 100 => throw new CustomExceptionOne(f"Passed arg $x%d")
      case _ => throw new CustomException002(f"Passed arg $arg%d")
    }
  }
}