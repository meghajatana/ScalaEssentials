package part2.loop

import scala.language.postfixOps

class Person(val name: String, favouriteMovie: String, val age: Int =0) {
  def +(other: Person): String = s"${this.name} and ${other.name} is hanging out"

  def +(subtitle: String): Person = new Person(s"${name} ($subtitle)", favouriteMovie, age)

  def unary_+ : Person = new Person("${name}", favouriteMovie, age + 1)

  def learns(topic: String) = s"$name learns $topic"

  def learnsScala: String = learns("Scala")

  def apply: String = s"Hi My name is $name and my favourite movie is $favouriteMovie"

  def apply(num: Int) = s"$name watched $favouriteMovie $num times"
}
  object Person extends App  {
    val mary = new Person("Mary", "Inception")
    val tom = new Person("Tom", "Mission Impossible")
    println(mary + tom)
    println((mary + "the rockstar").apply)
    println((mary.apply))
    println(tom.apply)
    //println(mary learnsScala)
    println(mary.apply)
      println(tom.apply)
      println(mary(2))
      println(tom(3))
  }


