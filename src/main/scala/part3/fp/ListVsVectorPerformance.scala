package part3.fp

import scala.util.Random

object ListVsVectorPerformance extends App {

  val random = new Random()
  val maxRuns = 1000
  val maxCapacity = 1000000

  def getWriteTime(collection: Seq[Int]): Double = {
    val times = for {
      iterations <- 1 to maxRuns
    } yield {
      val currentTime = System.nanoTime()
      collection.updated(random.nextInt(maxCapacity), random.nextInt)
      System.nanoTime() - currentTime
    }
    times.sum * 1.0 / maxRuns
  }

 val numbersList = (1 to maxCapacity).toList
 val numbersVector = (1 to maxCapacity).toVector

 println(getWriteTime(numbersList))
 println(getWriteTime(numbersVector))
}
