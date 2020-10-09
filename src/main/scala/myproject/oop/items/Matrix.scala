package myproject.oop.items


class Matrix {

  var values:Array[Int] = null
  var w: Int = 0
  var h: Int = 0

  def this (width: Int, height: Int) = {
    this()
    this.w = width
    this.h = height
    values = new Array[Int](width * height)
  }

  def fill(value: Int): Unit = {
    values = Array.fill[Int](w * h)(value)
  }

  def apply(xInd:Int, yInd:Int): Int = {
    values(w * yInd + xInd)
  }

  def update(xInd:Int, yInd:Int, numVal: Int) = {
    values(w * yInd + xInd) = numVal
  }

  def rotated(): Matrix = {
    val res = new Matrix(h, w)
    var (xx, yy, idx) = (0,0,0)
    for(i <- 0 until values.length){
      xx = i / h // reading old list: matrix X
      yy = h - 1 - i % h // reading old list: matrix Y
      idx = xx + w * yy // reading old list: index
      res.values(i) = values(idx)
    }
    res
  }

  override def toString(): String = {
    var str = ""
    var maxLen = 0
    val maxValLen = values.max.toString.length
    val fNum = (num: Int) => s"%${maxValLen}s".format(num.toString)
    for(row <- 0 until h){
      val subArray = values.slice(row * w, row * w + w)
      val rowStr = subArray.map(fNum).mkString(" ")
      val rowLen = rowStr.length
      str += rowStr + "\n"
      if(maxLen < rowLen){
        maxLen = rowLen
      }
    }
    val border = "-" * maxLen
    str = border + "\n" + str + border
    return str
  }
}

//object Matrix {
//  def empty(x:Int, y:Int): Matrix = {
//    return new Matrix(0,0)
//  }
//}
