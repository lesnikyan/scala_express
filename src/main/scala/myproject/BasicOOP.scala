package myproject

object BasicOOP extends Demo {
  override def run(): Unit = {
    val itnum = new ItemNum(11)

    val strnum = new ItemStrNum("Str-Num-Name")

    val itval1 = new ItemVal(1)
    val itval17 = new ItemVal(17)

    val seq1:List[Int]  = itval17.seq(5, 3)
    pf("ItemVal(17).seq(5, 3) = size: %d, seq: %s", seq1.size, seq1)

    val pair = new NumPair(5)
    ps(pair)
    pf("is twins = %b", pair.isTwins)
    pair.y = 77
    ps(pair)
    pf("is twins = %b", pair.isTwins)

  }
}

// no fields
class NoVal{

}

// no constructor
class SomeVal {

  var x: Int = 0

  def multVal(arg: Int) = arg * x
}


// Simple constructor
class ItemNum(var x: Int){

  // string representation
  override def toString: String = "ItemNum(%d)".format(x)
}


// several constructors
class ItemStrNum(val name: String, num: Int = 0) extends ItemNum(num) {

  // overloaded constructor
  def this(){
    this("Def-Name", 100500)
  }

  // overridden string representation
  override def toString = "ItemStrNum(%d, %s)".format(name, num)
}


// Accessor / mutator
class NumPair(val x: Int){

  // instance variable
  var _y = x

  // accessor
  def y = _y

  // mutator
  def y_=(value: Int) = _y = value

  // boolean property
  def isTwins = x == y

//  def operator= = new NumPair(1)

  override def toString: String = "%s(%d, %d)".format(this.getClass.getSimpleName, x, y)

}


class ItemVal(val x: Int) {

  // body of main constructor
  val minimalSeq = List(x)

  // methods

  // addition
  def mult(arg: Int): Int = x * arg

  // multiplication
  def sum(arg: Int) = x + arg

  /**
    * Instance method
    * Generate number sequence from x to x * `size` over `step`
    * x = 1, step = 2, size = 3:
    * List(1, 3, 5)
    *
    * @param size
    * @param step
    * @return
    */
  def seq(size:Int, step: Int = 1) : List[Int] = {

    /**
      *  Local function
      *  Generate sequence based on previous elements and target size:
      *
      *  step = 2
      *  List(5, 3, 1) --> List(7, 5, 3, 1)
      *
      * @param prev
      * @return
      */
    def make_seq(prev: List[Int]): List[Int] = {
      if (prev.size == size) {
        return prev
      } else {
        return make_seq(prev.head + step :: prev)
      }
    }

    make_seq(minimalSeq).reverse

  }

}

