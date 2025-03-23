package part2.loop

object Generics extends App {

  class MyList[+A] {
def add[B >: A](element: B): MyList[B] = ???
/*
* A = Cat
B = Animal
* */
  }

  class MyMap[Key, Value]

  val listOfIntegers = new MyList[Int]
  val listOfStrings = new MyList[String]


  object MyList {
    def empty[A]: MyList[A] = ???
  }
  val emptyListOfIntegers = MyList.empty[Int]
}

//Variance Problem
class Animal
class Cat extends Animal
class Dog extends Animal

//1) yes list[Cat] extends List[Animals] = COVARIANCE
class CovariantList[+A]
val animal: CovariantList[Animal] = new CovariantList[Cat]
// animalList.add(new Dog)???

//2) No = INVARIANCE
class InvariantList[A]
val invariantAnimalList: InvariantList[Animal] = new InvariantList[Animal]

//3) Hell, no! CONTRAVARIANCE
class Trainer[-A] {
val trainer: Trainer[Cat] = new Trainer[Animal]
}

//boundedTypes
class Cage[A <: Animal](animal: A)
val cage = new Cage(new Dog)

class Car
//val newCage = new Cage(new Car)