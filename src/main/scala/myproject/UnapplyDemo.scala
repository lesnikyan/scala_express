package myproject

object UnapplyDemo extends Demo {
  def run = {
    val Strings3(a1, a2, a3) = " aa bb cc dd  "
    println("3 parts: " + (a1, a2, a3))

    // class unfolding
    val user = new UserOD("Vasya", 23)
    val UserOD(uName, uAge) = user
    println("User: (%s, %d)".format(uName, uAge))

    // One-value unfolding

    val ToInt(size) = "128"
    println("Size = %d, type = %s".format(size, size.getClass.getName))
  }
}

object Strings3 {
  def unapply(src: String):Some[(String, String, String)] = {
    val realParts = src.trim().split("\\s+")
    val eSize = if(realParts.size < 3) 3 - realParts.size else 0
    val emptyParts = Array.fill[String](eSize)("")
    val r: Array[String] = realParts.slice(0, 3) ++ emptyParts
//    println(r)
//    println("Res#1: " + (r(0), r(1), r(2)))
    Some((r(0), r(1), r(2)))
  }
}

class UserOD(val name: String, var age: Int)

object UserOD {
  def unapply(arg: UserOD) = {
    Some((arg.name, arg.age))
  }
}

object ToInt {
  def unapply(arg: String): Option[Int] = Some(arg.toInt)
}
