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

    pf("P5 orgg: %s", p5)
    p5 += p3
    pf("P5 changed: %s", p5)

    val p20 = p005 >> 20
    pf("p005 >> 20 = %s", p20)

    val p10 = 10 >>: p005
    pf("10 << p005: %s", p10)

    val p005minus = - p005
    pf("- p005: %s", p005minus)

    pf("p5 * 100 = %s", p005 * 100)
  }
}


class Pair(var x: Double, var y: Double){

  def +(arg: Pair): Pair = new Pair(x + arg.x, y + arg.y)

  def -(arg: Pair): Pair = new Pair(x - arg.x, y - arg.y)

  def *(k: Number): Pair = new Pair(x * k.doubleValue(), y * k.doubleValue())

  def ==(arg: Pair) = x == arg.x && y == arg.y


  /**
    * x or y not equals
    * @param arg
    * @return
    */
  def !=(arg: Pair) = ! (arg == this)

  /**
    * both x and y not equals
    * @param arg
    * @return
    */
  def <>(arg: Pair): Boolean = x != arg.x && y != arg.y


  def >>(value: Double): Pair = new Pair(x + value, y + value)

  // left-associative operators.
  // it should be ended by colons  (:)
  def >>:(value: Double): Pair = this >> value

  def `maxX`(arg: Pair): Pair = {
    if(x > arg.x) this else arg
  }

  // unary operator
  def unary_- = new Pair(-x, -y)

  override def clone: Pair = new Pair(x, y)

  override def toString: String = "%s(%f, %f)".format(this.getClass.getSimpleName, x, y)

}