package myproject

object ListOperatorDemo extends Demo {

    override def run(): Unit = {
      creating
      strings
      custom
    }

    def creating = {
      ps("Create")

      var list1 = 1::2::3::Nil
      list1 = 4::list1
      ps(list1)

      var list2 = 7::List(5,6)
      ps(list2)

      var list3 = list1 ::: list2
      list3 = list3 ++ (8::9::Nil)
      ps(list3)

      // head

      // tail

      // take

      // drop

      // reverse


    }

    def strings = {
      var s1 = "abc"
      var s2 = s1 ++ "de"
      ps(s2)
    }

    def custom = {
      var lizt1 = new Lizt[Int]()
      lizt1 <+ 111 <+ 222
      102 +: 101 +: lizt1
      lizt1 <++ 333 :: 444 :: Nil
      ps(lizt1)

      val lizt2 = new Lizt(77 :: Nil)
      val lizt3 = 55 :: 66 :: lizt2
      val lizt4 = lizt1 ++ lizt3
      ps(lizt4)

      ps(lizt4.map((x => "#%s!".format(x))))
    }

  }

  class Lizt[A] (var data:List[A] = Nil) {

    def <+(value: A): Lizt[A] = { data = data ++ List(value); this }

    def <++(value: List[A]): Lizt[A] = { data = data ++ value; this }

    def ++(value: Lizt[A]): Lizt[A] = new Lizt[A](data ++ value.data)

    def +:(value: A): Lizt[A] = { data = value::data; this }

    def ::(value: A): Lizt[A] = { new Lizt(value::data) }

    def map[B](f:(A) => B): Lizt[B] = new Lizt(data.map(f))

    override def toString: String = "Lizt(%s)".format(data.mkString(", "))
  }
