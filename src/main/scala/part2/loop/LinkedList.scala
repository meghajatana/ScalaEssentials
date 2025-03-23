package part2.loop

import scala.language.postfixOps

/*object MyList {
  def apply(newData: Int) = new MyList{newData, null}
}*/

trait MyTransformer[-A, B] {
  def convert(toBeTransformed: A): B
}

trait MyPredicate[-T] {
  def test(v: T): Boolean
}

   abstract class MyList[+A] {
     def head: A
     def tail: MyList[A]
     def isEmpty: Boolean
     def addNewElement[B >: A](newElement: B): MyList[B]
     def printElements: String
     override def toString: String = "["+ printElements +"]"
     def map[B](transform: MyTransformer[A, B]): MyList[B]
     def flatMap[B](transformer: MyTransformer[A, MyList[B]]):MyList[B]
     def filter(predicate: MyPredicate[A]): MyList[A]
     def ++[B >: A](list:MyList[B]):MyList[B]
     def foreach(fn: A => Unit): Unit
     def sort(fn:(A, A) => Int): MyList[A]
     def ::[B >: A](elem: B): MyList[B]


     //higher-Order Functions
     def map1[B](transform: A => B): MyList[B]
     def flatMap1[B](transformer: A => MyList[B]): MyList[B]
     def filter1(predicate: A => Boolean): MyList[A]
     def zipWith[B,C](list: MyList[B], f:(A,B)=> C) :MyList[C]
     def fold[B >: A](initValue: B)(fn: (B,A)=> B): B
   }

  case object Empty extends MyList[Nothing] {
    def head: Nothing = throw new NoSuchElementException
    def tail: MyList[Nothing] = throw new NoSuchElementException
     def isEmpty: Boolean = true
    def addNewElement[B >: Nothing](newElement: B): MyList[B] = Cons(newElement, Empty)
    override def printElements: String = ""

    def map[B](myTransformer: MyTransformer[Nothing, B]):MyList[B] = Empty
    def flatMap[B](transformer: MyTransformer[Nothing, MyList[B]]): MyList[B] = Empty
    def filter(predicate: MyPredicate[Nothing]): MyList[Nothing] = Empty
    def ++[B >: Nothing](list: MyList[B]): MyList[B] = list

    def map1[B](myTransformer: Nothing => B): MyList[B] = Empty
    def flatMap1[B](transformer: Nothing => MyList[B]): MyList[B] = Empty
    def filter1(predicate: Nothing => Boolean): MyList[Nothing] = Empty

    override def ::[B >: Nothing](elem: B): MyList[B] = Cons(elem, Empty)
    def foreach(fn: Nothing => Unit): Unit = ()

    def sort(fn: (Nothing,Nothing) => Int): MyList[Nothing] = Empty
    //def zipWith[B](list: MyList[B], f: (Nothing, Nothing) => B): MyList[B] = if(list.isEmpty) Empty else list

    override def zipWith[B,C](list: MyList[B], f: (Nothing, B) => C): MyList[C] = if(list.isEmpty) Empty else throw new RuntimeException("Lists do not have the same length")

    def fold[B >: Nothing](initValue: B)(fn: (B,Nothing) => B):B =initValue

  }

  case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
    def head: A = h

    def tail: MyList[A] = t

    def isEmpty: Boolean = false

    def addNewElement[B >: A](newElement: B): MyList[B] = Cons(newElement, this)

    override def printElements: String = {
      if (t.isEmpty) "" + h else s"$h ${t.printElements}"
    }

    def filter(predicate: MyPredicate[A]): MyList[A] = if (predicate.test(h)) Cons(h, t.filter(predicate)) else t.filter(predicate)

    def map[B](transform: MyTransformer[A, B]): MyList[B] = Cons(transform.convert(h), t.map(transform)) //else t.map(transform.convert(t.head))

    def ++[B >: A](list: MyList[B]): MyList[B] = Cons(h, t ++ list)

    def ::[B >: A](elem: B): MyList[B] = Cons(elem, Cons(h, t))

    def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B] = transformer.convert(h) ++ t.flatMap(transformer)


    def filter1(predicate: A => Boolean): MyList[A] = if (predicate(h)) Cons(h, t.filter1(predicate)) else t.filter1(predicate)

    def map1[B](transform: A => B): MyList[B] = Cons(transform(h), t.map1(transform)) //else t.map(transform.convert(t.head))

    def flatMap1[B](transformer: A => MyList[B]): MyList[B] = transformer(h) ++ t.flatMap1(transformer)

    //HOFs
    def foreach(fn: A => Unit): Unit = {fn(h)
      t.foreach(fn)}
    def sort(compare: (A, A) => Int): MyList[A] = {
    def insertionSort(x:A, sortedList:MyList[A]):MyList[A] = {
      if(sortedList.isEmpty) Cons(h, Empty)
      else if (compare(x, t.head) < 0) Cons(x, sortedList)
      else Cons(sortedList.head, insertionSort(x, sortedList.tail))
    }
      insertionSort(h, t.sort(compare))
    }
      def zipWith[B,C](list: MyList[B], f: (A, B) => C): MyList[C] = if(list.isEmpty) throw new RuntimeException("Lists do not have the same length")
      else Cons(f(h, list.head), this.tail.zipWith(list.tail, f))

    def fold[B >: A](initValue: B)(fn: (B,A) => B): B = t.fold(fn(initValue,h))(fn)
  }

    //MyListFunctions
    //- map(MyTransformer)-> MyList[] e.g.[1,2,3].map(n*2) = [2,4,6]
    //filter(predicate) -> MyList e.g. [1,2,3,4].filter(n%2) = [2,4]
    //flatMap(transformer from A to MyList[B])=> MyList[B]  e.g. [1,2,3].flatMap(n => [n, n+1]) => [1,2,2,3,3,4]

    object LinkedList extends App {
      val listOfIntegers: MyList[Int] = Cons(1, Cons(2, Cons(3, Cons(4, Empty))))
      val cloneListOfIntegers: MyList[Int] = Cons(1, Cons(2, Cons(3, Cons(4, Empty))))
      println(listOfIntegers.head)
      println(listOfIntegers.tail)
      println(listOfIntegers.isEmpty)
      println(listOfIntegers.addNewElement(5))
      println(listOfIntegers.toString)
      println(listOfIntegers.map(
        new MyTransformer[Int, Int] {
          override def convert(toBeTransformed: Int): Int = toBeTransformed * 2
        }
      ))
      println(listOfIntegers.flatMap(new MyTransformer[Int, MyList[Int]] {
        override def convert(toBeTransformed: Int): MyList[Int] = Cons(toBeTransformed, Cons(toBeTransformed + 1, Empty))
      }))

      println(listOfIntegers.filter(
        new MyPredicate[Int] {
          override def test(v: Int): Boolean = v % 2 == 0
        }
      )
      )

      println(listOfIntegers.map1(_ * 2))
      println(listOfIntegers.flatMap1(elem => Cons(elem, Cons(elem + 1, Empty))))

      println(listOfIntegers.filter1(_ % 2 == 0
      )
      )
      println(listOfIntegers.foreach(println))
      println(listOfIntegers.sort((x:Int,y:Int)=>y-x))
      println(listOfIntegers.fold(0)(_+_))
      println(cloneListOfIntegers == listOfIntegers)

      val forComprehension = for {
        integers <- listOfIntegers
      } yield integers
      println("forComprehension: " + forComprehension)

      val listOfStrings: MyList[String] = Cons("a", Cons("b", Cons("c", Cons("d", Empty))))
      println(listOfStrings.head)
      println(listOfStrings.tail)
      println(listOfStrings.isEmpty)
      println(listOfStrings.addNewElement("e"))
      println(listOfStrings.toString)
      println(listOfIntegers.zipWith(listOfStrings, _ + _))
    }

