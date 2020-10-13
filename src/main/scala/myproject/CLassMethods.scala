package myproject

object ClassMethods extends Demo {

  override def run(): Unit = {

    val sun = new SomeUnit(1, 2, "aaa")
    sun.z = 4
    ps(sun.names)
//    ps(sun.names (7)) // method returns List with instance method apply
    ps(sun.nNames())
    ps(sun.nNames(2))
    ps(sun.nNames(5)(4))
    ps("Nums")
    ps(sun.nums(10))

  }
}


class SomeUnit(var x: Int, val y: Double, val name: String) {

  // property `z`

  var _z: Int = 0;

  def z = _z

  def z_=(value: Int) = _z = value

  // num sequence: x + y * (1 : n)
  def nums(n: Int) = for(i <- 1 to n) yield (x + i * y).toInt

  def nNames(n: Int = 0):List[String] = (for(i <- 1 to n) yield name).toList

  def names = nNames(z)

  def foo(a: Int)(b:Int) = a * b

  def foo(a:Int*) = a.fold(1)(_ * _)

}

