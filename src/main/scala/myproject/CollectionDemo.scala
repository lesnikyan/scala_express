package myproject

import scala.collection.SortedSet
import scala.collection.immutable.SortedMap
import scala.collection.mutable.{ArrayBuffer, LinkedHashMap}

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

    // mutable Array
    {
      ps("Mutable array (ArrayBuffer):")
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
      val nums = Set(1, 2, 3, 4, 5)
      val nums2 = Set(4, 5, 6, 7)

      pf("Set.union %s", nums.union(nums2))
      pf("Set ++ %s", nums ++ nums2)
      pf("Set a | b %s", nums | nums2)

      pf("Set.diff %s", nums.diff(nums2))
      pf("Set a -- b %s", nums -- nums2)
      pf("Set &~ %s", nums &~ nums2)

      pf("Set.intersect %s", nums.intersect(nums2))
      pf("Set & %s", nums & nums2)
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


    // Stream
    {
      ps("Stream")

      val nums:Stream[Int] = (1 to 10).toStream
      ps(nums.getClass.getName)
      ps(nums.getClass.getSimpleName)
      ps(nums.head)
      ps(nums.tail.map(_ * 10).force)
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
    }


    // Recursive processing
    {

      //        val intermVals = new ArrayBuffer[Int]() // debug

      def sumList(list: List[Int], start: Int = 0): Int = {
        //          intermVals.append(start) // debug: intermid args
        if (list.isEmpty)
          start
        else
          sumList(list.tail, start + list.head)
      }

      val nums = List(1, 2, 3, 4, 5, 6, 7, 8, 9)

      val res = sumList(nums)
      pf("Recursive sum of List: %d", res)
      //        pf("demo1. startVals: %s", intermVals.mkString(", ")) // debug
    }


    // Map

    import scala.collection.immutable.SortedMap
    import scala.collection.mutable.{LinkedHashMap, Map => MutMap}

    {
      val colors = MutMap("red" -> 0xff0000, "green" -> 0xff00, "blue" -> 0xff, "badcolor" -> 0x123)
      colors("yellow") = 0xffff00
      colors += ("white" -> 0xffffff, "black" -> 0x0)
      colors -= "badcolor"
      ps(colors.keySet.mkString(", "))

      for ((k, v) <- colors){
        ps("%-10s: %06x".format(k, v))
      }

      ps("Inverted ->")
      val invColors = for((k,v) <- colors) yield (v,k)
      for ((k, v) <- invColors){
        ps("%06x: %s".format(k, v))
      }
    }

    {
      val userAges = SortedMap("Bob" -> 20, "Andrew" -> 22, "John" -> 30)
      for((k,v) <- userAges){
        ps("User: %s - %d".format(k, v))
      }

      val nums = LinkedHashMap("First" -> 1, "Second" -> 2, "Third" -> 3, "Fourth" -> 4)
      for((k, v) <- nums){
        ps("%-8s = %d".format(k, v))
      }
    }

    // Tuple

    {
      val t3 = (1, '2', "3")
      ps("tuple=(%d, %s, %s)".format(t3._1, t3._2, t3._3))

      val (one, two, three) = t3
      ps("vals=(%d, %s, %s)".format(one, two, three))

      // unfolding tuples
      val (a, b, c) = t3
      val (d, e) = (4, 5)
      val t5 = (a, b, c, d, e)
      ps(t5)

      // zip
      val names = Array("color", "size", "weight")
      val vals = Array("red", 3, 12.5)
      val pairs = names.zip(vals)
      pf("Pairs: %s | %s", pairs.getClass.getSimpleName, pairs.mkString(", "))

      val zippedMap = Map(pairs:_*)
      ps(zippedMap)
    }

  }
}
