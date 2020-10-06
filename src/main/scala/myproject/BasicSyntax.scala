package myproject


import scala.collection.immutable.Stream.Empty

object BasicSyntax extends Demo {
  def run = {

    // 2 spaces indentation

    // Val: immutable one

    val birthYear = 1990
    val isAdmin = true
    // isAdmin = false // ERROR
    val esrctOrbit = 149.6e9 // meters
    val firstChar = 'A'

    (Int, Byte, Char, Long, Float, Double, Boolean) // Simple types

    (null, false, Nil, None, Unit, Empty) // several way to say: No

//    (Null, Nothing) // child of all types

    // parent types
//    (
    //    Any, // any at all
    //    AnyVal, // simple types
    //    AnyRef, // classes
    //    )

    import scala.math.{pow, round, E, Pi, sqrt}

    // any value is object

    val bigNum: Double = pow(10, 20).*(9e5f) // pow(10, 20) * 9e5f
    val longNum = bigNum.toLong
    pf("Long value: %d", longNum)

    // Variable: mutable one

    var counter = 0
    counter += 1
    var isDone = false
    isDone = true

    // Type definition

    val name2: String = "Petryk Pyatochkin"
    var age: Int = 25
    val nums: List[Int] = List(11, 22, 33)

    // Strings

    val name = "Vasya"
    val fullName = name + " " + "Pupkin"
    val helloVasya = "Hello %s!".format(fullName)
    val strVals = "Format types: int: %d, double: %f, boolean: %b".format(123, 20.005, true)
    println(strVals)

    val multiLineStr =
      """
        |- String 111
        |- String 222
      """
    // as is
    ps("-- as is")
    ps(multiLineStr)
    // strip margins
    ps("-- strip margin")
    ps(multiLineStr.stripMargin)
    // trim lines
    ps("-- trim")
    ps(multiLineStr.stripMargin.trim)
    ps("-- x")

    // Array

    ps("-- Array --")
    var nums2 = Array(10, 20, 30)
    nums2 = nums2.reverse
    pa(nums2)

    val names: Array[String] = Array("Vasya", "Vova", "Goga")
    ps(names.toString)
    pa(names)
    pf("names: %s", names.mkString(" #|# "))
    pf("Array(0): %s", names(0))


    // List

    ps("-- List --")
    val names2: List[String] = names.toList

    val years = List(2001, 2002, 2003)
    val nextYears = 2004 :: 2005 :: 2006 :: List()
    val nextNext = 2007 :: 2008 :: 2009 :: Nil

    var numList = List(1, 2, 3, 4, 5)
    val numPart2 = 6 :: 7 ::8 :: 9 :: Nil
    val numPart3 = List(10, 11, 12)
    numList = numList ::: numPart2 // reassign with new List
    numList = numList ++ numPart3 // reassign with new List
    pf("numList: %s", numList)


    // Map

    val colors = Map("red" -> 0xff0000, "green" -> 0x00ff00, "blue" -> 0x0000ff)

    import scala.collection.mutable.{Map => MutMap}

    val darkColors = MutMap("red" -> 0x880000, "brown" -> 0x4422000)
    darkColors += ("green" -> 0x00ff00)

    val darkBlue = ("blue" -> 0x000088)
    darkColors += darkBlue
    darkColors += Tuple2("gray", 0x444444)


    // Function

    ps("-- Functions --")

    // define function
    def biSum(a: Int, b: Int): Int = a + b

    pf("biSum(12, 5) = %d", biSum(12, 5))

    // val with function
    val triSum: (Int, Int, Int) => Int  = (a: Int, b: Int, c: Int) => a + b + c

    pf("triSum(10, 200, 3000) = %d", triSum(10, 200, 3000))

    // COLOR output
    import myproject.utils.{AnsiColors => AnC}

    println(AnC.colored("Print RED", AnC.RED))

  }
}
