package part3.fp

object MapFlatMapFilterFor extends App {

  val list = List(1, 2, 3)
  println(list.head)
  println(list.tail)

  //map
  println(list.map(_ + 1))
  println(list.map(_ + " is a number"))

  //filter
  println(list.filter(_ % 2 == 0))

  //flatMap
  val toPair = (x: Int) => List(x, x + 1)
  println(list.flatMap(toPair))

  //print all combinations between 2 lists
  val numbers = List(1, 2, 3, 4)
  val chars = List('a', 'b', 'c', 'd')
  val colors = List("black", "white")

  //iterating
  println(numbers.flatMap(num => chars.flatMap(ch => colors.map(clr => clr + " " + ch + num))))

  // foreach
  list.foreach(println)

  //for-comprehensions

  val forComprehensions = for {
    n <- numbers if (n % 2 == 0)
    c <- chars
    clr <- colors
  } yield clr + " " + c + n

  println(forComprehensions)

  //forEach
  for {
    n <- numbers if (n % 2 == 0)
  } print(n + " ")
}
