package myproject

import scala.util.matching.Regex

object MatchingDemo extends Demo {
    def run = {


        // like switch
        var res1 = 0
        val condVal = "four"
        condVal match {
            case "one" => res1 = 1
            case "two" => res1 = 2
            case "four" => res1 = 4
        }
        ps("res 1:", res1)

        // like switch returning result
        val condVal2 = "one"
        val res2 = condVal2 match {
            case "one"  => 1
            case "two"  => 2
            case "four" => 4
            case _ => 5 // default case
        }
        ps("res 2:", res2)

        // if condition

        val nums3 = Map("one" -> 1, "two" -> 2, "tree" -> 3)
        var res3 = 0.0
        val condVal3 = "tree"
        var a3 = 1
        var foo3 = (x:Int) => math.pow(x.toDouble, 2)

        condVal3 match {
            case "+" => foo3 = (x:Int) => x + x
            case _ if(nums3.keySet.contains(condVal3)) => a3 = nums3(condVal3)
            case _ => a3 = 10
        }
        res3 = foo3(a3)
        ps("res 3:", res3)

        // var matching

        val cond4 = "123"
        val res4 = cond4 match {
            case "456" => "last"
            case arg => "first(%s)".format(arg)
        }

        val cond5 = 5
        val res5 = cond5 match {
            case 3 => 3
            case arg if(arg < 10) => 2 * arg
            case _ => 10
        }
        ps("res 5:", res5)

        // Name conflict between constant and case argument
        val maxVal = 10
        val cond6 = 7
        val res6 = cond6 match {
            case curVal if curVal < maxVal / 2 => curVal * 10 // maxVal - is pre-defined const
            case `maxVal` => 1000                             // maxVal - is pre-defined const
            case maxVal if maxVal >= `maxVal` => 100 * `maxVal` // // maxVal - is a local arg
            case _ => 5
        }
        ps("res 6:", res6)

        // Option matching # 7
        val cond7: List[Option[String]] = List(Some("One"), Some("Two"), Some("Tree"), None, null)
        for(item <- cond7){
            item match {
                case Some(str) => ps("Option:", str)
                case None => ps("None item")
                case _ => ps("Non-option value")
            }
        }

        // Option in map # 8
        val cond8 = Map("one" -> 1, "two" -> 2, "three" -> 3)
        val keys8 = List("one", "two", "three", "four")
        for(key <- keys8) {
            for (item <- cond8.get(key)){
                ps("map val: ", item)
            }
        }

        // PartialFunction # 9
        // https://www.scala-lang.org/api/current/scala/PartialFunction.html
        val f:PartialFunction[Any, String] = {
            case a:Int => ("Int number %d".format(a))
            case b:String => ("String value '%s'".format(b))
            case null => ("NULL value")
            case _ => ("Unknown case")
        }

        val cond9 = List("Hello!", 123, 1.0, null)
        for(item <- cond9){
            ps(f(item))
        }


        // 10. OR cases

        val orCaseVals = for(x <- 1 to 7) yield {
            (x, x match {
                case 1 => "one"
                case 2 | 3 => "2-3"
                case 4 | 5 | 6 => "4-6"
                case _ => "other"
            })
        }
        ps(s"OR-cases: $orCaseVals")


        // 11. Type mathcing
        val obbVals : Array[Any] = Array("a  b c", 128)
        for(obb <- obbVals){
            obb match {
                case a: Int => ps("int value (%d)".format(a))
                case b: String => ps("just string: '%s'".format(b))
            }
        }

        // 12. Enheritance case
        val obSs = List(new A(), new B(), new Z(), "Something", 
            Array("1qqq", "2qqq", "3qqq"))
        for(obs <- obSs){
            obs match {
                case a: A => ps(a.oneMsg)
                case b: B => ps(b.threeMsg)
                case z: Z => ps(z.msg)
                case arr: Array[String] => ps("Arr:(%s)".format(arr.mkString(", ")))
                case _ => ps("Unexpected type of object!")
            }
        }

        // 13. Array vals mathcing
        val arrList13 = List(Array(1), Array(0,1), Array(1,2,3, 7, 7, 7), Array())
        for(elem <- arrList13){
            elem match {
                case Array(1) => ps("Array with 1")
                case Array(0, second) => ps("Array case2: %d".format(second))
                case Array(a, b, c, _*) => ps("Array case3: %d, %d, %d".format(a, b, c))
                case _ => ps("Not expected value.")
            }
        }

        // 14. List mathcing
        val listList14 = List(List(1), List(0, 2), List(1, 2, 3, 4, 5, 6), List(11, 22, 33), Nil)
        for(elem <- listList14){
            elem match {
                case 0 :: Nil => ps("List(zero)")
                case 0 :: aaa :: Nil => ps("List(zero, %d)".format(aaa))
                case aa :: _ :: _ :: Nil => ps("List(%d, _, _)".format(aa))
                case a :: b :: c :: tail => ps("List vals: %d %d %d".format(a, b, c))
                case _ => ps("Default case.")
            }
        }

        // 15. Tuple
        val tup15 = List(Tuple1(1111), (11, 22), (100, 200, 300))
        for(elem <- tup15){
            elem match {
                case Tuple1(a) => pf("Tuple1: %d", a)
                case (a , b) => pf("Tuple2: %d ; %d", a, b)
                case (100, b, _) => pf("Tuple3: %d ", b)
                case _ => ps("Tuple: unexpected value.")
            }
        }

        // 16. class matching, unapply
        val users16 = List(new User("Vasya", 20), new User("Petya", 20), 
            new User("Olya", 30), null)
        for(user <- users16){
            user match {
                case User("Vasya", age) => pf("Vasya user: %d", age)
                case User(name, 30) => pf("Age 30 user: %s", name)
                case User(name, age) => pf("User: (%s %d)", name, age)
                case _ => ps("Not valid user.")
            }
        }

        // 17. regex
        val rxNameNum: Regex = "([a-zA-Z]{3,15})\\s([0-9]+)".r
        val rxNumName = "([0-9]+)\\s([a-zA-Z]+)".r
        val rxNameStr = "([a-zA-Z]+)\\s([a-zA-Z\\-]+)".r
        val srcList17 = List("Vasya 12345", "Vova 2222", "123 Kalyan", "Mari 333abc", "Mykola One-Two-Ten")
        for(src <- srcList17){
            src match {
                case rxNameNum(name, num) => pf("1 Name:Num(%s, %s)", name, num)
                case rxNumName(num, name) => pf("2 Name:Num(%s, %s)", name, num)
                case rxNameStr(name, num) => pf("3 Name:Num(%s, %s)", name, num)
                case noMatch => pf("Regex: No matches in: '%s'", noMatch)
            }
        }

        // 18. Variable definition matching
        val (a18, b18) = (101, 102)
        pf("tuple ufold: (%d, %d)", a18, b18)
        val arr18 = Array(11, 22, 33)
        val Array(c18, d18, _*) = arr18
        pf("array unfold: [%d, %d]", c18, d18)
        val list18 = List(21, 22 ,23, 24, 25)
        val e18 :: f18 :: _ :: h18 :: tail = list18
        pf("List unfold: [%d, %d, %d]", e18, f18, h18)

        // 19. for loop with variable matching
        import scala.collection.mutable.ArrayBuffer
        val list19 = List((1, "a"), (2, "b"), (3, "c"), (4, ""),
            (5, "d"), (6, ""), (7, "e"), (8, ""))
        val arrb19 = new ArrayBuffer[String]()
        val oneCharRx = "[a-z]{1}".r
        for ((n19, s19:String) <- list19 if s19.length > 0){
            arrb19 += fm("N:%d", n19)
        }
        pf("Unfold in for: %s", arrb19.mkString(", "))

        // 20. Case-classes
        val zList20 = List(ACase(20.0), BCase("red", 0xff0000), BCase("blue", 0x0000ff),
            CCase("man", "bootball"), CCase("girl", "shoping"), DCase("D-111"),  null)
        for(zz <- zList20){
            zz match {
                case ACase(size) => pf("case-A: size = %f", size)
                case BCase(name, num) => pf("case-B: (%s, %d)", name, num)
                case CCase("man", value) => pf("case-C: for man: %s", value)
                case "girl" CCase value => pf("case-C: for girl: %s", value)
                case myCase: ZeroCase => pf("zero: info = %s\n\ttoString: %s", myCase.info, myCase)
                case _ => ps("Case match: unexpected object")
            }
        }


        val zListList21 = List(
            List(ACase(20.0), BCase("red", 0xff0000)),
            List(ACase(10.0), BCase("blue", 0x0000ff)),
            Array(ACase(20.0), CCase("citizen", "walking")),
            Array(CCase("man", "bootball"), CCase("girl", "shoping")),
            (ACase(30.0), BCase("white", 0xffffff), CCase("abuga", "baga"))
        )

        for(item <- zListList21){
            item match {
                case ACase(size1) :: BCase(name2, value2) :: Nil =>
                    pf("Match21: List2: A(%.1f), B('%s', %d)", size1, name2, value2)
                case  Array(unit1 @ CCase(_, _), unit2 @  CCase(_, _)) =>
                    pf("Match21: arr[C('%s', '%s'), C('%s', '%s')]",
                        unit1.bum, unit1.bam, unit2.bum, unit2.bam)
                case Array(ACase(size), bb @ CCase(_, _)) => pf("M21: AC: %.1f %s %s", size, bb.bum, bb.bam)
                case (aa:ACase, bb:BCase, cc:CCase) => pf("M21:Tuple3: %.1f ! %s #%06x ! %s - %s",
                    aa.size, bb.name, bb.color, cc.bum, cc.bam)
                case _ => ps("Match21: default.")
            }
        }

    }

}

// Type-matching example

trait S {
    val msg: String
}

class A extends S {
    val msg = "Aaaa"

    def oneMsg = msg
}

class B extends S{
    val msg = "Bbbb"

    def threeMsg = List.fill(3)(msg).mkString(" ")
}

class Z extends S {
    val msg = "Zzzz"
}

class User(val name: String, val age: Int){

}

object User{
    def apply(name:String, age:Int): User = new User(name, age)

    def unapply(u: User): Option[(String, Int)] = {
        if(u != null) Some((u.name, u.age)) else None
    }
}

// Case classes example

abstract class ZeroCase{
    def info = "Ordinar Z-case"
}

case class ACase(size: Double) extends ZeroCase

case class BCase(name: String, color: Int) extends ZeroCase

case class CCase(bum: String, bam: String) extends ZeroCase

case class DCase(name: String) extends ZeroCase {
    override def info = f"Customized D-case: $name"
}
