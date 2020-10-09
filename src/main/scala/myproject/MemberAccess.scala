
package myproject


object MemberAccessDemo extends Demo {
    override def run(): Unit = {
        val memAcc = new MemberAccess()
        ps("read/write:origin:", memAcc.c)
        memAcc.c = 11
        ps("read/write:changed:", memAcc.c)

        memAcc.changeVal

        // BeanProperty
        memAcc.setEee(33)
        ps("BeanProperty:", memAcc.getEee)

        val submac = new SubMemberAccess()
        submac.changeF(44)
    }
}


import myproject.PrintUtils._

import scala.beans.BeanProperty


class MemberAccess {


    // 1. public member
    var a = 0

    // 2. readonly field
    val b = 1000

    // 3. read and write methods
    private var cVal = 0 // Cant be less than 0

    def c = cVal // Reader

    def c_=(newValue: Int){ if(newValue >=0) cVal = newValue } // Writer

    // 4. Object-private, access only for the same instance of class
    private[this] var d = 0

    def valOfD = d

    // 5. Class private.

    class InnerType{

        // type private
        private[MemberAccess] var d = 0

        def valOfD = d

    }

    def changeVal(){
        val stp = new InnerType()
        stp.d = 22
        ps("sub.class-private:", stp.valOfD)
    }

    // 6. JavaBean Property
    // generates: getEee, setEee
    @BeanProperty var eee = 0

    // 7. protected
    protected var f = 0

}

class SubMemberAccess extends MemberAccess {
    def changeF(newValue: Int){
        f = newValue
        ps("Sub:protected:", f)
    }
}
