package part2.loop

object CaseClasses extends App {

  case class Person(name: String, age:Int)

  //1. class parameters are fields
  val jim = Person("Jim",34)
  println(jim.name)

  //2. sensible toString
  //println(instance) = println(instance.toString) //syntactic sugar
  println(jim)

  //3. equals and hashcode implemented OUT oF THE BOX
  val jim2 = Person("Jim",34)
  println(jim ==jim2)

  //4. CCs have handy copy methods
  val jim3 = jim.copy(age = 45)
  println(jim3)

  //5. CCs have companion objects
  val thePerson = Person
  val mary = Person("Mary", 23)

  //6. CCs are serializable
  //Akka

  //7. CCs have extractor patterns = CCs can be used in pattern matching
  case object UnitedKingdom {
    def name: String = "The UK of GB and NI"
  }


}
