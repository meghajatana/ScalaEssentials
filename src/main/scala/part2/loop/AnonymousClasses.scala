package part2.loop

object AnonymousClasses extends App {
abstract class Animal {
  def eat: Unit
}

val funnyAnimal: Animal = new Animal {
  override def eat: Unit = println("Hahahaha")
}
/*
* class AnonymoysClass extends Animal {
*   override def eat: Unit = println("Hahahaha")
* }
* class part2.loop.AnonymousClasses$$anon$1
* */
println(funnyAnimal.getClass)
}

class Person1(name: String) {
  def sayHi: Unit = println(s"Hi, my name is")
}

 val jim = new Person1("Jim") {
  override def sayHi: Unit = println(s"Hi, my name is Jim ")
}



