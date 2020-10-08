package myproject;

//  reserved symbols cannot be overridden:
// _ : = => <- <: <% >: # @

object OperatorsDemo extends Demo {
  override def run(): Unit = {

    var p5 = new Pair(5, 5)
    val p3 = new Pair(3, 3)

    val p005 = p5.clone

    pf(
      "orig: %s, copy: %s, is same =  %b",
      p5.hashCode,
      p005.hashCode,
      p5.hashCode == p005.hashCode)

    pf("p5 orig: %s", p5)
    p5 += p3
    pf("p5 changed: %s", p5)

    pf("p005 - p3 + new Pair(18, 18) = %s", p005 - p3 + new Pair(18, 18))

    pf("p5 * 100 = %s", p005 * 100)

    val p20 = p005 >> 20
    pf("p005 >> 20 = %s", p20)

    // Left associativity
    val p10 = new Pair(10, 10)
    val p5_10 = p10 >>: p005 // the same as: ps(p005.>>:(p10))
    pf("p10 >>: p005: %s", p5_10)

    // Unary operator
    val p005minus = - p005
    pf("- p005: %s", p005minus)
  }
}


class Pair(var x: Double, var y: Double){

  def +(arg: Pair): Pair = new Pair(x + arg.x, y + arg.y)

  def -(arg: Pair): Pair = new Pair(x - arg.x, y - arg.y)

  def *(k: Number): Pair = new Pair(x * k.doubleValue(), y * k.doubleValue())

  def ==(arg: Pair) = x == arg.x && y == arg.y

  // x or y not equals
  def !=(arg: Pair) = ! (arg == this)

  // both x and y not equals
  def <>(arg: Pair): Boolean = x != arg.x && y != arg.y

  // get Point that is shifted to some value by x and same y
  def >>(value: Double): Pair = new Pair(x + value, y + value)

  // left-associative operators.
  // it should be ended by colons  (:)
  def >>:(arg: Pair): Pair = this + arg

  def `maxX`(arg: Pair): Pair = {
    if(x > arg.x) this else arg
  }

  // unary operator
  def unary_- = new Pair(-x, -y)

  override def clone: Pair = new Pair(x, y)

  override def toString: String = "%s(%f, %f)".format(this.getClass.getSimpleName, x, y)

}