package part3.fp

object AnonymousFunctions extends App {
   def doubler: Int => Int = (x: Int) => x * 2
   
   val incrementer: Int => Int = _ + 1
  val niceAdder:(Int, Int) => Int = _ + _
  val justDoSomething: () => Int = () => 3
  
  println(doubler(4))
  println(niceAdder(4,5))
  println(incrementer(4))
  println(justDoSomething) //function itself
  println(justDoSomething()) //function call
  
}
