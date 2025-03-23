package part3.fp

object SocialNetwork extends App {

  case class Person(name: String)
  val network: Map[Person, Set[Person]] = Map()
  val cultElite: Map[Person, Set[Person]] = Map(megha-> Set(minal), neetu -> Set(vaishali), vaishali -> Set(neetu), minal-> Set(megha))
  val renu = Person("Renu")
  val neetu = Person("Neetu")
  val minal = Person("Minal")
  val megha = Person("Megha")
  val pearl = Person("Pearl")
  val namrata = Person("Namrata")
  val vaishali = Person("Vaishali")
  val kk = Person("kajal")

  def addToNetwork(network:  Map[Person, Set[Person]], person: Person): Map[Person,Set[Person]] = {
    network + (person-> Set.empty[Person])
  }

  def removeFromNetwork(network:  Map[Person, Set[Person]], person: Person): Map[Person, Set[Person]] = {
    val updatedNetwork: Map[Person, Set[Person]] = network.map(x => x._1 -> x._2.filterNot(a => a== person))
    updatedNetwork - person
  }

  def removeFromNetworkRecursive(network: Map[Person, Set[Person]], person: Person): Map[Person, Set[Person]] = {
    def auxiRemove(friends:Set[Person], networkAcc: Map[Person, Set[Person]]): Map[Person, Set[Person]] ={
      if(friends.isEmpty) networkAcc else auxiRemove(friends.tail, removeFriend(networkAcc,person,friends.head))
    }
    val unfriendedNetwork = auxiRemove(network(person), network)
   unfriendedNetwork - person
  }

  def addFriend(network:  Map[Person, Set[Person]], person: Person, friend:Person): Map[Person, Set[Person]] = {
    val x = network.updated(person, network(person) + friend)
      x.updated(friend, network(friend) + person)

    network + (person-> (network(person) + friend)) + (friend-> (network(friend) + person))
    /*for {
    n <- network if n._1 == person
    f <- network if f._1 == friend
  } yield ((n._1,n._2:+friend),(f._1,f._2:+person))*/
  }

  def removeFriend(network:  Map[Person, Set[Person]], person: Person, friend: Person): Map[Person, Set[Person]] = {
    network + (person -> (network(person) -friend)) + (friend -> (network(friend) - person))
  }

  def friendCountMapping(network:  Map[Person, Set[Person]]):Map[Person, Int] = network.view.mapValues(_.size).toMap

  def bigFriendCircle(network:  Map[Person, Set[Person]]) = friendCountMapping(network).maxBy(a => a._2)._1

  def noFriends(network:  Map[Person, Set[Person]]) = network.count(_._2.isEmpty)

  def checkSocialConnection(network:  Map[Person, Set[Person]], person1: Person, person2:Person) = {
    network(person1).contains(person2) || network.exists(a => a._2.contains(person2) && a._2.contains(person1))
  }

  def checkSocialConnectionBFS(network: Map[Person, Set[Person]], person1: Person, person2: Person) = {
    def bfs(target: Person, consideredPeople: Set[Person], discoveredPeople: Set[Person]): Boolean = {
      if(discoveredPeople.isEmpty) false
      else {
        val person = discoveredPeople.head
        if(person ==  target) true
        else if(consideredPeople.contains(person)) bfs(target, consideredPeople, discoveredPeople.tail)
        else bfs(target, consideredPeople + person, discoveredPeople ++ network(person))
      }
    }
    bfs(person2,Set(),network(person1)+person1) //|| bfs(person1,Set(),network(person2)+person2)
  }
  val addingNeetu = addToNetwork(network, neetu)
  val addingVaishali = addToNetwork(addingNeetu, vaishali)
  val addingNamrata = addToNetwork(addingVaishali, namrata)
  val addingMinal = addToNetwork(addingNamrata, minal)
  val addingMegha = addToNetwork(addingMinal, megha)
  val addingPearl = addToNetwork(addingMegha, pearl)
  val addingRenu = addToNetwork(addingPearl, renu)
  val addingKk = addToNetwork(addingRenu, kk)
  val addMeghaFriends1 = addFriend(addingKk,megha, pearl)
  val addMeghaFriends2 = addFriend(addMeghaFriends1, megha, minal)
  val addNeetuFriend1 = addFriend(addMeghaFriends2, neetu, vaishali)
  val addNeetuFriend2 = addFriend(addNeetuFriend1, neetu, renu)
  val addNeetuFriend3 = addFriend(addNeetuFriend2, neetu, pearl)
  val addRenuFriend1 = addFriend(addNeetuFriend3, renu, pearl)
  val addVaishaliFriend1 = addFriend(addRenuFriend1, vaishali, pearl)
  val addMinalFriend1 = addFriend(addVaishaliFriend1, minal, pearl)
  val addNamrataFriend1 = addFriend(addMinalFriend1, namrata, pearl)
  val addNamrataFriend2 = addFriend(addNamrataFriend1,minal, namrata)
  val personFriendCount = friendCountMapping(addNamrataFriend2)
  val personWithMaximumFriends = bigFriendCircle(addNamrataFriend2)
  val unfriendVaishaliPearl = removeFriend(addNamrataFriend2, vaishali, pearl)
  val removePearlFromNetwork = removeFromNetwork(addingPearl, pearl)
  println(addingRenu)
  println(personFriendCount)
  println(personWithMaximumFriends)
  println(noFriends(addNamrataFriend2))
  println(addNamrataFriend2)
  //println(unfriendVaishaliPearl)
  val testConnection = addFriend(removeFriend(removeFriend(removeFriend(removeFriend(addNamrataFriend2,neetu,pearl),vaishali,pearl),renu,pearl),minal,pearl),neetu,minal)
  println(testConnection)
  println(checkSocialConnection(testConnection, neetu, pearl))
  println(checkSocialConnectionBFS(testConnection,neetu, pearl))
  println(removeFromNetwork(addNamrataFriend2, pearl))
  println(removeFromNetworkRecursive(addNamrataFriend2, pearl))


}
