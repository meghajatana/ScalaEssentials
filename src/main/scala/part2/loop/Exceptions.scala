package part2.loop

object Exceptions extends App {

  val x: String = null
  //println(x.length)
  //this ^^ will crash with NPE
  //throwing and catching exceptions

  //val aWierdValue: String =  throw new NullPointerException()

  private def getInt(withExceptions: Boolean): Int = if(withExceptions) throw new RuntimeException("No int for you!")
    else 42

    val potentialFail = try {
      getInt(true)
    } catch {
      case e: RuntimeException => 43
    } finally {
      println("finally")
    }
    println(potentialFail)

    class StackOverflowException(message: String) extends Exception
    class StackUnderflowException(message: String) extends Exception
   class MatchCalculationException(message: String) extends Exception



    object PocketCalculator {
      def  add(x: Int, y: Int): Int = {
        val result = x + y
        if(x > 0 && y> 0 && result < 0) throw new StackOverflowException("Max Int value reached") else if(x < 0 && y <0 && result > 0) throw new StackUnderflowException("Min Int value reached") else result
      }
      def  subtract(x : Int, y: Int): Int = {
        val result = x - y
        if(x > 0 && y> 0 && result < 0) throw new StackOverflowException("Max Int value reached") else if(x < 0 && y <0 && result > 0) throw new StackUnderflowException("Min Int value reached") else result
      }
      def  divide(x: Int, y: Int): Float = if(y!=0) x/y else throw new MatchCalculationException("Divisor cannot be 0")
      def multiply(x:Int, y:Int): Int = {
        val result = x * y
        if(x > 0 && y< 0 && result > 0) throw new StackUnderflowException("Min Int value reached")
        else if(x < 0 && y > 0 && result > 0) throw new StackUnderflowException("Min Int value reached")
        else if(x < 0 && y < 0 && result < 0) throw new StackOverflowException("Max Int value reached")
        else if(x > 0 && y > 0 && result < 0) throw new StackOverflowException("Max Int value reached")
        else result}
    }

    PocketCalculator.add(Int.MinValue, -10)
    //OOM
    //val array = Array.ofDim(Int.MaxValue)

    //SO
    //def infinite: Int = 1 + infinite
    //val noLimit = infinite
}
