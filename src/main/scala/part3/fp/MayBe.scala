package part3.fp

trait MayBe[+A] {
  def isEmpty: Boolean
  def map[B](fn: A => B): MayBe[B]
  def flatMap[B](fn: A => MayBe[B]): MayBe[B]
  def filter(condition: A => Boolean): MayBe[A]
}

case object MayBeNot extends MayBe[Nothing] {
  def isEmpty: Boolean = true
  def map[B](fn: Nothing => B): MayBe[B] = MayBeNot
  def flatMap[B](fn: Nothing => MayBe[B]): MayBe[B] = MayBeNot
  def filter(condition: Nothing => Boolean): MayBe[Nothing] = MayBeNot
}

case class SomeElement[+A](elem: A) extends MayBe[A] {
  def isEmpty: Boolean = false
  def map[B](fn: A => B): MayBe[B] = SomeElement(fn(elem))
  def flatMap[B](fn: A => MayBe[B]): MayBe[B] = fn(elem)
  def filter(condition: A => Boolean): MayBe[A] = if(condition(elem)) SomeElement(elem) else MayBeNot
}

object MayBe extends App {
  val mayBe:MayBe[Int] = SomeElement(10)
  val mayBeEmpty:MayBe[Int] = MayBeNot
  println(mayBe.map(_*5))
  println(mayBeEmpty.map(_*5))
  println(mayBe.flatMap(a => SomeElement(a%2==0)))
  println(mayBeEmpty.map(a => SomeElement(a%2==0)))
  println(mayBe.filter(_ % 3==0))
  println(mayBeEmpty.map(_ % 3==0))
}
