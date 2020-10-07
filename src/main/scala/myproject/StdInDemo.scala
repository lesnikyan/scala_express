package myproject

object StdInDemo extends Demo {

  override def run(): Unit = {

    import scala.io.StdIn._

    ps("Enter string")
    val strVal = readLine()
    pf("val: %s", strVal)

    ps("Enter Int")
    val intVal = readInt()
    pf("val: %d", intVal)
  }

}
