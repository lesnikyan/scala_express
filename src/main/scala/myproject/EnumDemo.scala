
package myproject

object EnumDemo extends Demo {
    def run {
        val v1:SimpleEnum.Value = SimpleEnum.First
        println("simple: %d, %s|%s".format(v1.id, v1, v1.toString))
        println(SimpleEnum.values.mkString(", "))

        import WeekDay._

        def printDay(day: WeekDay.Value) = {
            pf("Day: %d, %s | %s", day.id, day, day.toString, day)
        }

        val days = List(Mon, Wed, Fri)
        for(day <- days){
            printDay(day)
        }

    }
}

object SimpleEnum extends Enumeration  {
    val Zero, First, Second, Third, Fourth, Fifth = Value
}

//object TunedEnum extends Enumeration {
//    val Pedro = Value(100, "working")
//    val John = Value(110, "having-fun")
//    val Mixailo = Value(120, "thinking")
//}

object WeekDay extends Enumeration {
    val Sun = Value(1, "Sunday")
    val Mon = Value(2, "Monday")
    val Tue = Value(3, "Tuesday")
    val Wed = Value(4, "Wednesday")
    val Thu = Value(5, "Thursday")
    val Fri = Value(6, "Friday")
    val Sat = Value(7, "Saturday")

}
