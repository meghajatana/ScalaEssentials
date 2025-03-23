package part3.fp

object TuplesAndMaps extends App {

  val map = Map(("Jim"-> 555), ("JIM", 999))
  println(map.map(pair => (pair._1.toLowerCase,pair._2)))
}
