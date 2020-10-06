package myproject

trait Utils {

  def pf(msg:String, args:Any*){
    ps(fm(msg, args:_*))
  }

  def fm(msg:String, args:Any*): String = msg.format(args:_*)

  def ps[T](msg: T){
    println(msg)
  }

  def ps(){
    ps("")
  }

  def pa[T](arg:Array[T]){
    pf("%s(%s)", arg.getClass.getSimpleName, arg.take(32).mkString(", "))
  }

  def pt[T](obj: T): Unit ={
    print(obj)
  }

  def pt(): Unit ={
    pt("")
  }
}