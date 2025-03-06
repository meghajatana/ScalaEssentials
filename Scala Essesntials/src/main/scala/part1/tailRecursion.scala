package part1

import scala.annotation.tailrec

object tailRecursion extends App {

  def fibo(n: Int): Int = {
    @tailrec
    def fibonacciTailRec(n: Int, nextToPrev: Int = 1, prev: Int = 1): Int = {
      if (n <= 2) prev else fibonacciTailRec(n - 1, prev, nextToPrev + prev)
  }
    fibonacciTailRec(n)
  }
//1 1 2 3 5 8 13 21
  println(fibo(9))

  def factorial(n: Int): Int = {
    @tailrec
    def factTailRec(n: Int, acc:Int = 1): Int =
      if(n<=1) acc else factTailRec(n-1, acc * n)
    factTailRec(n)
  }
println(factorial(20))
}
