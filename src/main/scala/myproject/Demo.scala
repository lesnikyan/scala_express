package myproject

trait Demo extends Utils {
  def run(): Unit;

  def demo = {
    pf("\n-- run %s --", this.getClass.getSimpleName)
    run
  }

}
