package part2.loop

object AbstractDataTypes extends App {
  abstract class Animal {
    val creatureType: String
    def eat: Unit
  }

  class Dog extends Animal {
    val creatureType = "Canine"
   def eat = println("crunch crunch")
  }

  trait Carnivore {
    def eat(animal:Animal) :Unit
  }

  class Crocodile extends Animal with Carnivore {
    override val creatureType: String = "croc"

    override def eat: Unit = println("nomnomnom")

    override def eat(animal: Animal): Unit = println(s"I'm a croc and I'm eating ${animal.creatureType}")
  }

  val dog =  new Dog
  val croc = new Crocodile
  croc.eat(dog)

}
