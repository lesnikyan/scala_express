package myproject

object InheritDemo extends Demo {

  override def run = {
    val a = new Aaa("aaa")
    println(a.nameRepeat(3))
    val a2 = new Aaa2("aaa22", 5)
    println(a2.nameRepeat(2))

    // type check
    if(a2.isInstanceOf[Aaa]){
      val aa = a2.asInstanceOf[Aaa]
      println("aa: " + aa.nameRepeat(4))
      if(aa.isInstanceOf[Aaa2]){
        val a22 = aa.asInstanceOf[Aaa2]
        println("a22:" + a22.nameRepeat(3))
      }
    }

    if(a2.getClass != classOf[Aaa]){
      println("a2 not a Aaa")
    }

    // override vars
    val merc = new Mercedes()
    println("merc: " + merc)

    // Anonymous
    val bmw = new Car("BMW"){ override val color = "red"}
    println("bmw: " + bmw)

    // usage of val of subclass
    val a5 = new A5()
    println("a05: " + a5.s)

    val a10 = new A10()
    println("a10: " + a10.s)

    // eq,  equals
    val teqa = new TeqA(11, 100f)
    println("teqa: " + teqa)

    val teqb = new TeqB(22, 200f, "Second-Level")
    println("teqb: " + teqb)

    val teqa2 =  new TeqA(11, 100f)
    println("teq1==teq2: " + (teqa == teqa2))

    val teqb2 = new TeqB(11, 100f, "First-Level")
    println("teq1==teqb2: " + (teqa == teqb2))
    println("teq1==null: " + (teqa == null))
    println("teqa.hashCode = %016x".format(teqa.hashCode))

    // test inherit Java
    val scTest = new ScTest
    scTest.stest
  }
}


// simple class with constructor and method
class Aaa(val name: String){

  def nameRepeat(num: Int = 1): String = {
    val names = for {a <- 0 until num} yield name
    names.mkString(" ")
  }
}


// inheritance with declared constructors
// inherited arg `name` without declaration `val`
class Aaa2(name: String, size: Int) extends Aaa(name) {

  override def nameRepeat(num: Int=2): String = {
    "[%s]".format(super.nameRepeat(num))
  }
}


abstract class Vehicle {
  val color: String
}


// inherit abstract
class Car(brand: String) extends Vehicle {
  // inherited abstract field hasn't to use word `override`
  val color = "white";

  def size = 10

  // override def by def
  override def toString = {
    "car %s, color: %s".format(brand, color)
  }
}

// pass const arg to super constructor
class Mercedes extends Car("Mercedes-Benz"){

  // override val by val
  override val color = "black"

  // override def by val
  override val size = 15
}


// Example of early initialization section
class A5 extends Aaa("A") {
  val a = 5
  def b = "B"
  val s = nameRepeat(a)
}

class A10 extends {
  // val s creates using value of A10.a instead of A5.a
  override val a = 10

  // can`t override method in early initialization section like:
  // override def b = "<b>"
} with A5


class TeqA (val a: Int, val b: Float) {
  override def toString() = "TeqA(%d, %.2f)".format(a, b)

  override def equals(other: Any) = {
    val that = other.asInstanceOf[TeqA]
    if(that == null) false
    else that.a == a && that.b == b
  }

  override def hashCode: Int = a.hashCode * 17 + b.hashCode * 23
}

// inherit class using and passing args from constructor
class TeqB (a: Int, b: Float, val c: String) extends TeqA(a, b){
  override def toString() = "TeqA(%04d, %.3f, \"%s\")".format(a, b, c)
}


// inherit java class from the same project, nothing
import jmain2.JTest2

class ScTest extends JTest2 {
  def stest = {
    println("Scala subclass test. Then java super:")
    super.test()
  }
}

