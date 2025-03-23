package part2.loop

object Objects extends App {

  //Scala does not have Class-Level Functionality ("static")

  object Person {
    val N_EYES = 2
    def canFly: Boolean = false

    def apply(mother: Person, Father: Person): Person = new Person("Bobbie")

  }

  class Person(name: String) {
  }
  println(Person.N_EYES)
  println(Person.canFly)
  val mary = new Person("Mary")
  val john = new Person("John")
  println(mary == john)

  val person1 = Person
  val person2 = Person
  println(person1 == person2)
  
  val bobbie =  Person(mary, john)
}