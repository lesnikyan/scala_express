package myproject

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


    // partial func
  }
}
