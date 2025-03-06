package part2.loop

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
    def flatMap[B](transformer: MyTransformer[A , MyList[B]]): MyList[B] = transformer.convert(h) ++ t.flatMap(transformer)

  }

    /*class Filter[T] extends MyPredicate[T] {
      override def test(v:T): Boolean = v match {
        case int: Int => int % 2 == 0
        case string: String => string.contains("m")
        case char: Char => char == 'c'
        case listOfString: List[String] => listOfString.isEmpty
      }
      //override def test[T](v: T, con: T => T): Boolean = v./(2)
    }*/

    /*class Transform[-A,B] extends MyTransformer[A,B] {
      override def convert[A, B](toBeTransformed: A): B = toBeTransformed match {
        case int: Int => (int * 2).asInstanceOf[B]
        case string: String => (string + ">").asInstanceOf[B]
        case char: Char => (char*2).asInstanceOf[B]
        case listOfString: List[String] => (listOfString::listOfString).asInstanceOf[B]

    }*/

    //MyListFunctions
    //- map(MyTransformer)-> MyList[] e.g.[1,2,3].map(n*2) = [2,4,6]
    //filter(predicate) -> MyList e.g. [1,2,3,4].filter(n%2) = [2,4]
    //flatMap(transformer from A to MyList[B])=> MyList[B]  e.g. [1,2,3].flatMap(n => [n, n+1]) => [1,2,2,3,3,4]

   object LinkedList extends App {
     val listOfIntegers: MyList[Int] = Cons(1, new Cons(2, new Cons(3, new Cons(4, Empty))))
     val cloneListOfIntegers: MyList[Int] = Cons(1, new Cons(2, new Cons(3, new Cons(4, Empty))))
     println(listOfIntegers.head)
     println(listOfIntegers.tail)
     println(listOfIntegers.isEmpty)
     println(listOfIntegers.addNewElement(5))
     println(listOfIntegers.toString)
     println(listOfIntegers.map(
       new MyTransformer[Int, Int] {
         override def convert(toBeTransformed: Int): Int = toBeTransformed*2
     }
   ))
     println(listOfIntegers.flatMap(new MyTransformer[Int, MyList[Int]] {
       override def convert(toBeTransformed: Int): MyList[Int] = Cons(toBeTransformed, new Cons(toBeTransformed+1, Empty))
     }))

     println(listOfIntegers.filter(
       new MyPredicate[Int] {
         override def test(v: Int): Boolean = v%2==0
       }
     )
   )
   println(cloneListOfIntegers == listOfIntegers )

     val listOfStrings: MyList[String] = Cons("a", new Cons("b", new Cons("c", new Cons("d", Empty))))
     println(listOfStrings.head)
     println(listOfStrings.tail)
     println(listOfStrings.isEmpty)
     println(listOfStrings.addNewElement("e"))
     println(listOfStrings.toString)
   }
