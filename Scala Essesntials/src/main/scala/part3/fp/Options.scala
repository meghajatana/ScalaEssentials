package part3.fp

import scala.util.Random

object Options extends App {

  val config: Map[String, String] = Map(
    "host" -> "176.45.36.1",
    "port" -> "80"
  )

  class Connection {
    def connect = "Connected" //connect to some server
  }

  object Connection {
    val random = new Random(System.nanoTime)

    def apply(host:String, port: String): Option[Connection] =
      if(random.nextBoolean()) Some(new Connection) else None
  }

 println(Connection(config.getOrElse("host","120.34.56.3"), config.getOrElse("port","8080")).map(_.connect))
 val isConnected = for{
   host <- config.get("host")
   port <- config.get("port")
   connection <- Connection(host, port)
 } yield connection.connect

  isConnected.foreach(println)
}
