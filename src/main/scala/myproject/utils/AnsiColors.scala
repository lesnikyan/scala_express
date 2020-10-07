package myproject.utils

object AnsiColors {
  val RESET = "\u001B[0m"
  val BLACK = "\u001B[30m"
  val GRAY = "\u001B[30;1m"
  val RED = "\u001B[31m"
  val GREEN = "\u001B[32m"
  val YELLOW = "\u001B[33m"
  val BLUE = "\u001B[34m"
  val PURPLE = "\u001B[35m"
  val CYAN = "\u001B[36m"
  val WHITE = "\u001B[37m"

  def printColor(color: String) = {
    println(colored("color: " + color, BLUE))
  }

  def printColor(color: Int) = {
    println(colored("color: [#%08x]".format(color), RED))
  }

  def printColor(color: Colors.Value) = {
    println(colored("color: <%s>".format(color), GREEN))
  }

  def colored(text: String, highlight: String) = {
    "%s%s%s".format(highlight, text, RESET)
  }

  def colorByName(name: String) = 1 // TODO

  // Enum
  object Colors extends Enumeration {
    val White, Gray, Black, Red, Blue, Green, Yellow, Orange, Purple = Value
  }

}
