package part4.patternMatching

import part2.loop.{Cons, MyList, Empty}

object AllThePatterns extends App {
  val x: Any = "scala"
  //1 - Constants
  val constants = x match {
    case 1 => "A number"
    case "scala" => "TheScala"
    case true => "TheTruth"
    case AllThePatterns => "A singleton object"
    case _ => ""
  }

  // 2- match anything
  //2.1 wildcard
  val matchAnything: Unit = x match {
    case _ =>
  }

  //2.2 variable
  val matchAVariable = x match {
    case something => s"I've found $something"
  }

  //3.Tuples
  val aTuple = (1,2)
  val matchATuple = aTuple match {
    case(1,1) =>
    case (something,2) => s"I've found $something"
  }

  //PMs can be nested
  val nestedTuple = (1,(2,3))
  val matchANestedTuple = nestedTuple match {
    case (_, (2, v)) =>
  }

  //4.Case classes - constructor Pattern
  //PMs can be nested wih case classes as well
  val aList: MyList[Int] = Cons(1, Cons(2, Empty))
  val matchAList = aList match {
    case Empty =>
    case Cons(head, Cons(subhead, subtail)) =>
  }

  //5.List Patterns
  val aStandardList = List(1,2,3,42)
  val standardListMatching = aStandardList match {
    case List(1, _,_,_,_) => //extractor - advanced
    case List(1,_*) => //list of arbitrary length - advanced
    case 1::List(_) => //infix pattern
    case List(1,2,3) :+42 => //infixPattern
  }

  //6. type specifiers
  val unknown: Any = 2
  val unknownMatch = unknown match {
    case list: List[Int] => //explicit type specifier
    case _ =>
  }

  //7- name binding
  val nameBindingMatch = aList match {
    case nonEmptyList @ Cons(_,_) => // name binding  => use the name later here
    case Cons(1, rest @ Cons(2,_)) => // name bindings inside nested patterns
  }

  // 8- multi-patterns
  val multiPattern: Unit = aList match {
    case Empty | Cons(0, _) => //compound pattern (multi-pattern)
    case _ =>
  }

  // 9 - if guards
  val secondElementSpecials = aList match {
    case Cons(_, Cons(specialElement, _))  if specialElement % 2 ==0  =>
  }

  val numbers: List[Int] = List(1,2,3,4)
  val numbersMatch = numbers match {
    case stringList: List[String] => "list of string"
    case numberList: List[Int] => "list Of Numbers"
    case _ => ""
  }

  println(numbersMatch)


}
