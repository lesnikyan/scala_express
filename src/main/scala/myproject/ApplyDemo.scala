package myproject

object ApplyDemo extends Demo {

  override def run(): Unit = {

    // simple sequence: apply

    // display part of sequence
    def printSeq(seq: NumSeq, from: Int, toN: Int): Unit ={
      for(n <- from to toN){
        pt(seq(n)); pt(" ")
      }
      ps
    }

    ps("y = 5 * n + start")
    val funcN5 = (s: Int, n:Int) => s + 5 * n // sequence function
    val seqN5 = new NumSeq(1000, funcN5)
    printSeq(seqN5, 10, 20)


    ps("y = n^2 * 100 + 1")
    val seqExp2x100 = new NumSeq(1, (s, n) => s + n * n * 100)
    printSeq(seqExp2x100, 5, 15)


    // custom 2D Matrix
    import myproject.oop.items.Matrix

    val m1 = new Matrix(3, 5)
    m1.fill(0)

    var num = 100
    for(i <- 1 until 3){
      for(j <- 0 until 4){
        m1(i, j) = num
        num += 10
      }
    }
    ps(m1)

    // apply
    pf("<1> m1(1,2) = %d", m1(1,2))

    // update
    m1(1, 2) = 555
    pf("<2> m1(1,2) = %d", m1(1,2))

    val m2 = m1.rotated
    println(m2)

    val m3 = m2.rotated
    println(m3)

  }
}


/**
  * Make numerical instance with math function.
  *
  * @param start
  * @param func
  */
class NumSeq(val start: Int, val func: (Int, Int) => Int){

  /**
    * Return N-th member of sequence
    * @param n
    * @return
    */
  def apply(n : Int): Int = func(start, n)
}
