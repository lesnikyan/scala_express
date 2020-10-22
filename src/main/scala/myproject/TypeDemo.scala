package myproject

import scala.language.postfixOps

import scala.collection.mutable.{ArrayBuffer, ListBuffer}

object TypeDemo extends Demo {
  def run = {
    ps(" -- 1. Demo:Types --")

    // Chain call this.type
    ps(" -- 2. Chain Demo --")
    InstanceType.demo
    ChainsDemo.demo


    // inner classes
    ps(" -- 3. Inner Demo --")
    import myproject.InnerDemo.InnerContainer.InnerInnerClass

    val chainClassInst = new InnerInnerClass
    ps(chainClassInst.info)
    ps(InnerDemo.InnerContainer.info)

    // type as alias
    ps(" -- 4. Type alias --")
    TypeAlias.demo

    // Structural type:
    ps(" -- 5. Structural type --")
    StructuralType.demo
  }
}


import myproject.utils.PubUtils._

// this.type

object InstanceType {

  class Default {
    def foo(s: String) = s
  }

  object AStyle extends Default{
    override def foo(s: String) = "(%s)".format(s)
  }

  object BStyle extends Default {
    override def foo(s: String) = "<%s>".format(s)
  }

  object DemoPrinter {

    val defStyle = new Default

    var style: Default = defStyle

    val strList = ArrayBuffer[String]()

    def reset = {style = new Default }

    def set(st: Default): this.type = {style = st; this}

    def add(s: String): this.type = {strList.append(format(s)); this}

    private def format(s: String) = style.foo(s)

    //    def get = strList.mkString("\n")
    def get = strList.mkString(" , ")

    def print = {ps(get); strList.clear(); set(defStyle)}
  }

  def demo = {
    DemoPrinter.add("test-1").set(AStyle).add("aaa1").set(BStyle).add("bbb").print
    DemoPrinter add "test-2" set AStyle add "aaa2" set BStyle add "bbb" print
  }
}


// same: instance type
object ChainsDemo {

  class SuperUnit{

    var _aaa = ""

    var _bbb = ""

    def setA(v: String): this.type = {_aaa = v; this}

    def setB(v: String): this.type = {_bbb = v; this}

    def info = s"${_aaa}, ${_bbb}"
  }

  class SubUnit extends SuperUnit {
    var _ccc = 0

    def setC(v: Int) = _ccc = v

    override def info: String = s"${super.info}, c: ${_ccc}"
  }

  def demo = {
    val unit = new SubUnit()
    unit.setA("Abba").setB("Bim-Bom") setC 123
    ps(s"SubUnit: ${unit.info}")
  }

}

// inner
object InnerDemo {

  object InnerContainer {
    class InnerInnerClass {
      def info = "info: %s" format this.getClass.getName
    }

    def info = "info: ChainsDemo.InnerContainer.info"
  }

}

// type alias

package aaaa.bbbb.cccc {
  object Dddd {
    object Eeee {
      class Ffff {
        def info = "FFF.info: %s" format this.getClass.getName

        override def toString: String = "Ffff#" + hashCode()

        def getClone = new Ffff()
      }
    }
  }
}

object TypeAlias {

  type Abcdef = aaaa.bbbb.cccc.Dddd.Eeee.Ffff

  type AbcList = ListBuffer[Abcdef]

  def demo = {
    val abc = new Abcdef
    ps(abc.info)

    val abcList = new AbcList()
    abcList += abc += abc.getClone
    ps(abcList.mkString(" , "))
  }
}

// Structural type

object StructuralType {

  class ArgA {
    def foo(msg: String, num: Int) = "%s; %d".format(msg, num)
  }
  class ArgB {
    def foo(msg: String, num: Int) =  List.fill(num)(msg).mkString(",")
  }

  class Consumer(val str: String, var n: Int) {

    def bar(tpl: {def foo(msg: String, num: Int): String}) = tpl.foo(str, n)
  }


  def demo = {
    val cons = new Consumer("Hello!", 3)
    val tpA = new ArgA
    val tpB = new ArgB
    ps(cons.bar(tpA))
    ps(cons.bar(tpB))
  }
}


// Infix Types

object InfixType {
  import scala.collection.mutable.ArrayBuffer

  class Coll2Gen[T, K]{
    val items = ArrayBuffer[(T,K)]()

    def add(item:(T,K)) = items.append(item)
  }

  type x[A,B] = (A,B)
  type ~[D,C] = (D,C)

  def demo = {
    val coll = new Coll2Gen[String, Int]
    val itemA: String x Int = ("a", 1)
    val itemB = ("b", 2)
    val itemC: String ~ Int = ("c", 3)

    coll.add(itemA)
    coll.add(itemB)
    coll.add(itemC)
  }

}


// Existential types
///

// self type

object SelfType {

  trait Abc {
    def specialMethod: String
  }

  trait AbcUser {
    this: Abc =>
    def foo(s: String) = "FOO: %s, %s".format(specialMethod, s)
  }

  class AChild extends Abc {
    override def specialMethod = "AAA"
  }

  class Target01 extends AChild with AbcUser {
    def getMsg = foo("Target01")
  }

  // ! Incorrect definition because not a child of Abc
  //  class Target02 extends AbcUser {
  //    def getMsg = foo("Target01")
  //  }

}
