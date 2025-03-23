package part3.fp

object HOFsCurries extends App {

  // Higher Order Function (HOF)
  val superFunction: (Int, (String, (Int => Boolean)) => Int) => (Int => Int) = null


  // map, flatMap, filter in MyList

  //nTimexFX(f,2, f(f(x))) = f(f(x))
def nTimesFX(f:Int => Int, n: Int, x: Int):Int = {
  if(n > 0) nTimesFX(f,n-1, f(x)) else x
}
  println(nTimesFX(_*2, 3, 2))

  def betterNTimesFX(f: Int => Int, n: Int): Int => Int = {
    if (n > 0) (x: Int) => betterNTimesFX(f, n - 1)(f(x)) else (x: Int) => x
  }

  val fx = betterNTimesFX(_ * 2, 3)
  println(fx(2))

  // functions with multiple parameter lists
  def curriedFormatter(c: String)(x: Double): String = c.format(x)

  val standardFormatter: Double => String = curriedFormatter("%4.2f")
  val preciseFormatter: Double => String = curriedFormatter("%10.8f")

  println(standardFormatter(Math.PI))
  println(preciseFormatter(Math.PI))

def toCurrying(f: (Int, Int) => Int): Int => Int => Int = {
  (x: Int) => (y: Int) => f(x,y)
}

  def fromCurrying(f: Int => Int => Int): (Int, Int) => Int = {
    (x: Int, y: Int) => f(x)(y)
  }

  //FunctionX Documentation
 def composeFOG[A,B,T](f:A => B, g: T => A): T => B = {
   x => f(g(x))
 }

 def andThen[A,B,C](f:A => B, g: B => C): A => C = {
   x => g(f(x))
 }

 println(toCurrying(_*_)(2)(3))
  println(fromCurrying((x:Int)=>(y:Int)=>x*y)(2,3))
  println(composeFOG[Int,Int,Int](_*2,_+3)(4))
  println(andThen[Int,Int,Int](_*2,_+3)(4))
}
