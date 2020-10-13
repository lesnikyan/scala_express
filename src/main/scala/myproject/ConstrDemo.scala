package myproject

import scala.collection.mutable.ArrayBuffer

object ConstrDemo extends Demo {

    def run = {
        // Constructors
        var items = new MyClass(11, 1f, "Aaa") :: new MyClass(22, 2f, "Bbb") :: new MyClass(33, 3f, "Ccc") :: Nil
        items = MyClass(44, 4f, "Ddd") :: items
        items = new MyClass() :: MyClass("Eee") :: items
        val items2 = MyClass(77, 7f, "Ggg") :: MyClass(88, 8f, "Fff") :: Nil
        items = items2 ::: items
        for(item <- items){
            println("item: ", item)
        }

        // Inner class
        val super1 = new MyClass("Val1")
        val super2 = MyClass("Val2")
        val sub1 = new super1.SubClass("SubOne")
        val sub2 = new super1.SubClass("SubTwo")
        val sub3 = new super2.SubClass("SubThree")
        val sub4 = new super2.SubClass("SubFour")

        val subs = new ArrayBuffer[MyClass#SubClass]()
        subs += sub1
        subs ++= List[MyClass#SubClass](sub2, sub3, sub4)
        for(s <- subs){
            println("sub: %s".format(s))
        }
    }
}

// for using fields and methods of companion object
import MyClass._

class MyClass(var x:Int, val y:Float, val name:String, private var id_ :Int=0){
    outer => // ref to self context

    if(id_ == 0){
        id_ = nextId
    } else {
        setLast(id)
    }

    def id = id_

    def this(name:String){
        this(0, 0, name)
    }

    def this(){
        this("Default")
    }

    class SubClass(val name: String){
        override def toString = "[(%d):%s.%s]".format(id_, outer.name, name)
    }

    override def toString = "[(%d) %s, %s, '%s']".format(id, x, y, name)

}

object MyClass{
    var lastId:Int = 0
    def nextId: Int = {lastId += 1; lastId}
    def setLast(value: Int): Unit = {lastId = value}

    def apply(name:String) = new MyClass(name)

    def apply(x:Int, y:Float, name:String) = new MyClass(x, y, name)

}
