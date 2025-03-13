package part3.fp

import scala.util.{Random, Try}

object HandlingFailure extends App {

  val hostname = "localhost"
  val port = "8080"
  def renderHTML(page: String) = println(page)

  class Connection {
    def get(url:String): String = {
      val random = new Random(System.nanoTime())
      if(random.nextBoolean()) "<html>...</html" else throw new RuntimeException("Connection interrupted")
    }
  }

  object  HttpService {
    val random = new Random(System.nanoTime())

    def getConnection(host: String, port: String): Connection
= if(random.nextBoolean())  new Connection else throw new RuntimeException("Someone else took the port")
  }

 for {
    connectionInitialization <- Try(HttpService.getConnection(hostname, port))
    connectionEstablishment <- Try(connectionInitialization.get(s"$hostname:$port"))
  } renderHTML(connectionEstablishment)


}
