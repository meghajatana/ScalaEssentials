package part2.loop

object Inheritance extends App {
  class Animal {
    val creatureType = "wild"
    protected def eat = println("nomnom")
  }

  class Cat extends Animal {
    def crunch = {
      eat
      println("crunch crunch")
    }
  }

  val cat = new Cat
  cat.crunch

  class Person(name: String, age: Int) {
    def this(name:String) = this(name, 0)
  }
  class Adult(name: String, age: Int, idCard: String) extends Person(name)
 class Dog(dogType: String) extends Animal {
   override val creatureType: String = dogType
   super.eat
   override def eat =  println("crunch crunch")
 }
val dog = new Dog("k9")
dog.eat
println(dog.creatureType)

val unknownAnimal: Animal = new Dog("Canine")
}
