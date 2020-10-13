package myproject

import scala.collection.mutable.ArrayBuffer

object FuncDemo extends Demo {
  override def run(): Unit = {

    // shortest function definition
    def hello = "Hello world!"

    // args and types
    def sum(a:Int, b:Int): Int = a + b


    // High order funcs: receibve or reutrn function

    // receive function

    def mapInt(seq:Seq[Int], func: Int => Int): List[Int] = {
      import scala.collection.mutable.ListBuffer

      val res = new ListBuffer[Int]()

      for(x <- seq){
        res.append(func(x))
      }
      res.toList
    }

    // use mapInt
    ps("mapInt:")
    val mapIntRes =
    {
      def exp2Mult100(x: Int) = x * x * 100

      val intSeq = 2 until 10
      pa(intSeq)
      mapInt(intSeq, exp2Mult100)
    }
    pf("res: %s", mapIntRes)

    // return function

    def strMultiplayer(num: Int):String => List[String] = {
      (str: String) => (0 until num).map(_ => str).toList
    }

    val strMult5 = strMultiplayer(5);
    pf("A-5: %s", strMult5("Aa"))
    pf("B-5: %s", strMult5("Bbb"))

    val strMult10 = strMultiplayer(10);
    pf("C-10: %s", strMult10("C-c-c"))


    // Lambdas and function usage
    ps("\n---")

    // Map

    def foo(x: Int): Int = 2 * x

    ps(Array(1, 3, 5, 7, 9).map(foo).mkString(", "))

    val foo3 = (x:Int) => x * 3

    ps("map. func: " + Array(1, 3, 5, 7, 9).map(foo3).mkString(", "))

    // Short syntax
    val foo100 = 100 * (_: Int)
    ps(List(9, 8, 7, 6, 5).map(foo100))

    val foo200: Double => Double = 200 * _
    ps(List[Double](1, 2, 3).map(foo200))

    ps("map.lambda: ")
    Array(1, 3, 5, 7, 9).map(_ * 5).map("<%d>".format(_)).foreach(print _ )


    // Reduce

    ps("reduce: " + List(1, 20, 300, 4000, 50000).reduce(_ + _) )

    val v2 = new Vals2(100, 25)
    ps(" lambda a + b: " + v2.mkRes(_ + _))
    ps(" lambda a * 1000 + b: " + v2.mkRes(_ * 1000 + _))

    val ooo = Ooo(1).o(Ooo(2))

    val o3 = List(Ooo(1),Ooo(2),Ooo(3)).reduce(_ o _)
    ps("o3: " + o3)


    //flatMap
    ps("fmap")
    ps(List(3, 4, 5).flatMap(x => List(x, x, x)))

    // Fold
    val folded_1_10 = (1 to 10).fold(0)(_ + _)
    pf("Folded 1 - 10: %d", folded_1_10)

    // Rfold
    val rfNums = 11 to 19

    ps("foldRight order:")
    rfNums.foldRight(0)((x, r) => {pt(x); pt(", "); 0})
    ps
    ps("foldLeft order:")
    rfNums.foldLeft(0)((r, x) => {pt(x); pt(", "); 0})
    ps

    val rfRes = rfNums.foldRight(List[Int]())((x, res) => x :: res) // from 19
    pf("foldRight: %s", rfRes)

    pf("foldLeft: %s", rfNums.foldLeft(List[Int]())((res, x) => x :: res )) // from 11

    // Closures
    val fgen5 = getFunc(5)
    ps("nums gen: " + fgen5(7))
    ps("nums gen: " + fgen5(123))

    val fgen10 = getFunc(10)
    ps("nums gen: " + fgen10(3))


    // partial func

    // Currying

    ps("mul 5 3: " + mul(5)(3))
    ps("mul 10 12: " + mul(10)(12))

    val mul3 = mul(3) _
    ps("mul3 (7): " + mul3(7))
    ps("mul3 (15): " + mul3(15))


    // Custom code blocks

    import scala.collection.mutable

    var ifkey = 5
    val ifres = mutable.ArrayBuffer[Int]()
    ifblock(ifkey > 0){
      ifres.append(ifkey)
      ifkey -= 1
    }
    println("ifblock: " + ifres.mkString(","))

    val loopRes = new mutable.ArrayBuffer[Int]()
    var i: Int = 0;
    forblock( i < 5, i += 1){
      loopRes.append(i * 10)
    }
    println("forblock: " + loopRes.mkString(","))

  }

  def mul(n:Int)(x:Int): Int = n * x

  def getFunc(arg: Int) = ((x:Int) => for{i <- 0 to arg} yield x)

  def ifblock(condition: => Boolean)(codeblock: => Unit) {
    if(condition){
      codeblock
      ifblock(condition)(codeblock)
    }
  }

  def forblock(cond: => Boolean, iter: => Unit)(block: => Unit) {
    if(cond){
      block
      iter
      forblock(cond, iter)(block)
    }
  }
}


class Vals2(val a: Int, val b: Int){
  def mkRes(f: (Int, Int) => Int) = f(a, b)
}

class Ooo(val x: Int) {

  def o(a: Ooo) = Ooo(x + a.x)

  override def toString = "Ooo(%d)".format(x)
}

object Ooo {
  def apply(a:Int) = new Ooo(a)
}