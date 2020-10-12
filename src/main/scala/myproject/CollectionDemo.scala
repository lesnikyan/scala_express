package myproject

import scala.collection.SortedSet
import scala.collection.mutable.ArrayBuffer

object CollectionDemo extends Demo {
  override def run(): Unit = {



    // Seq ---

    // Vector
    {
      val vec = Vector(11, 12, 13)
      val vec2 = vec :+ 21 // Vector(11, 12, 13, 21)
      pa(vec2)
      val vec3 = 1 +: vec // Vector(1, 11, 12, 13)
      pa(vec3)
    }

    // List
    {
      var list1 = 1::2::3::Nil
      list1 = 4::list1
      ps(list1)

      var list2 = 7::List(5,6)
      ps(list2)

      var list3 = list1 ::: list2
      list3 = list3 ++ (8::9::Nil)
      ps(list3)


      val names = "a1" :: "b2" :: "c3" :: "d4" :: "e5" :: Nil

      pf("Full list: %s", names)

      // head
      pf("Head: %s", names.head)

      // tail
      pf("Tail: %s", names.tail)

      // take
      pf("Take 2: %s", names.take(2))

      // drop
      pf("Drop 2: %s", names.drop(2))

      // reverse
      pf("Reverse: %s", names.reverse)

      // recursive processing
      // list and recursion
      {

//        val intermVals = new ArrayBuffer[Int]()

        def sumList(list: List[Int], start: Int = 0): Int = {
//          intermVals.append(start) // debug: intermid args
          if (list.isEmpty) start else sumList(list.tail, start + list.head)
        }

        val nums = List(1, 2, 3, 4, 5, 6, 7, 8, 9)

        val res = sumList(nums)
        pf("Recursive sum of List: %d", res)
//        pf("demo1. startVals: %s", intermVals.mkString(", "))
      }

    }

    // mutable Array
    {
      ps("Mutable array:")
      val arrb = ArrayBuffer(1, 2, 3)
      arrb.append(10)
      arrb.appendAll(List(21, 22, 23))
      arrb += 41
      arrb += (42, 43, 44)
      -1 +=: arrb
      List(-2, -3) ++=: arrb

      ps("arrb 1:", arrb)
      arrb -= 21
      arrb -= (22, 23)
      arrb --= List(41, 42 ,43)
      ps("arrb 2:", arrb ++ List(31, 32))
    }

    // Set
    {
      import scala.collection.immutable.Set
      val names = Set()
    }

    // Sorted Set
    {
      ps("Sorted set")
      import scala.collection.mutable.{SortedSet => MutableSortedSet}
      val names = MutableSortedSet[String]()
      names.add("Vova")
      names += "Vasya"
      names += ("Bob", "Loki", "Zorg")
      names += "Alex" += "Bella Bambina" + "Stepan Petrovych"
      names ++= List("Sarah Connor", "T-100", "Jack Daniels")
      pt("All names: ")
      for(n <- names){
        pt(n); pt(", ")
      }
      ps
    }

  }
}
