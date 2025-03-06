package part2.loop

class Writer(firstName: String, surname: String, val year: Int) {
  def fullName = s"$firstName $surname"
}
class Novel(name: String, yearOfRelease: Int, author: Writer) {
  def authorAge = yearOfRelease - author.year
  def isWrittenBy(author: Writer) = author == this.author
  def copy(newYearRelease: Int) = new Novel(name, newYearRelease, author)
}

class Counter(val count: Int = 0) {
  def increment = {
    println("incrementing")
    new Counter(count + 1)
  }
  def decrement = {
    println("decrementing")
    new Counter(count - 1)}
  def increment(n:Int): Counter = if(n <= 0) this else increment.increment(n - 1)
  def decrement(n: Int): Counter = if(n <= 0) this else decrement.decrement(n - 1)
  def print =  println(count)

}

object oopPractice extends App {
  val author = new Writer("Charles", "Dickens", 1852)
  println(author.fullName)
  val author1 = new Writer("Charles", "Dickens", 1852)
  val novel = new Novel("Best Way of Living Life", 1900, author)
  println(novel.authorAge)
  println(novel.isWrittenBy(author))
  println(novel.isWrittenBy(author1))
  println(novel.copy(1950))
  val counter = new Counter
  println(counter.count)
  val x = counter.increment(5)
    x.print
  x.decrement(5).print
  counter.increment.increment.increment.print
}
