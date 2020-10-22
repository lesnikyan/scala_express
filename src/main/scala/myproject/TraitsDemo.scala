package myproject

object TraitsDemo extends Demo {
  def run = {
    println("---" * 20 + "\n main.traits.Demo:")
    // as interface
    val animals = Array(
      new Dog("Muhtar"),
      new Dog("Bimbo"),
      new DecorFish(10f, "golden"))
    for(anim <- animals) println(anim.info)

    // as mixins
    val fus1 = new FuncUsage(10, 20, 500.0)
    println("FuncUsage: " + fus1.getInfo)

    // mixin during instantiating
    val nb = new NonBased("Red-Panda") with BaseTrait
    println(nb.about + ": " + nb.info(22, 55))

    // multilevel traits
    val tmes1 = new CombinedMsg2("MESSAGE_01")
    println(tmes1.nameMsg)

    // last trait is used first
    val tmes2 = new CombinedMsg("MESSAGE-02") with Msg1
    println(tmes2.nameMsg)

    val tmes3 = new CombinedMsg("MESSAGE-03") with Msg3 with Msg2 with Msg1
    println(tmes3.nameMsg)


    // constructor in trait
    // super class -> traits -> subclass
    val constr1 = new UseConstr01()

    // super class -> subclass -> traits
    val constr0 = new UseConstr() with ConstrT2 with ConstrT1

    // extended abstract field
    val constr3 = new UseCostr3("ConstrX03")
    println("constr3: " + constr3)
  }
}

// Inheritance, Trait as Interface

trait Animal {
  def info: String
}

class Dog(val name: String) extends Animal {

  def info: String = "Dog(%s)".format(name)
}

class DecorFish(val size: Float, val color: String) extends Animal {
  def info: String = "DecorFish[%s | %s]".format (size, color)
}

// mixins

trait BaseTrait {
  def info(a:Int, x:Double): String = "BaseInfo(%.1f; %d)".format (x, a)
}

trait FooTrait {
  def foo(a:Int, b:Int): Int = a + b
}

trait BarTrait {
  def bar(a: Double, b: Double): Double = a / b
}

class FuncUsage(a:Int, b:Int, c: Double) extends BaseTrait with FooTrait with BarTrait {
  def getInfo = info (foo(a, b), bar(c, a.toDouble))
}

class NonBased(val name: String){
  def about = "NonBased(%s)" format name
}

// multilevel traits

trait BaseMsg {
  def msg(m: String): String = "%s" format m
}

trait Msg1 extends BaseMsg {
  override def msg(m: String): String = super.msg("(%s)" format m)
}
trait Msg2 extends BaseMsg {
  override def msg(m: String): String = super.msg("=[%s]=" format m)
}

trait Msg3 extends BaseMsg {
  override def msg(m: String): String = super.msg("<<%s>>" format m)
}

abstract class AbstractMsg(val name: String){
  def nameMsg: String
}

class CombinedMsg(name: String) extends AbstractMsg(name) with BaseMsg {
  def nameMsg: String = msg(name)
}

class CombinedMsg2(name: String) extends AbstractMsg(name) with Msg1 with Msg2 with Msg3 {
  def nameMsg: String = msg(name)
}

// Trait constructor

trait ConstrT1 {
  val nums1 = List(1,2,3)
  println("constr trait ConstrT1 nums:List = " + nums1.mkString(", "))
}

trait ConstrT2 {
  val nums2 = Array(4, 5, 6)
  println("constr trait ConstrT2 nums:Array = " + nums2.mkString(", "))
}

class UseConstrSuper {
  println("constr class: UseConstrSuper")
}

// super class -> traits -> subclass
class UseConstr01 extends UseConstrSuper with ConstrT1 with ConstrT2 {
  println("constr class: UseConstr01")
}

class UseConstr extends UseConstrSuper {
  println("constr class: UseConstr0")
}

// abstract members

trait ConstrT3 {

  // abstract variable
  val name: String

  def nameInfo = "=[%s]=".format(name)
}

// implementation of abstract variable
class UseCostr3(val name: String) extends ConstrT3 {
  override def toString = "UseCostr3(%s)".format(nameInfo)
}


