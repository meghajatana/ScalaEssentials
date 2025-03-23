package part3.fp

object WhatsAFuction extends App {

  val stringConcatenation: (String, String) => String = new Function2[String, String, String] {
    override def apply(str1: String, str2: String): String = str1 + str2
  }

  println(stringConcatenation("Megha ", "Jatana"))

  val functionReturnFunction: Int => Int => Int = x => (y => x + y)

  println(functionReturnFunction(1)(2))

}


