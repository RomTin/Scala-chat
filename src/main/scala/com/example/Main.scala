package com.example

object Main {
  var users:List[User] = List(new User("unko", "unko", "unko", "unko"))
  //TODO
  def main(args: Array[String]): Unit = {
    println("\u001b[H\u001b[2J")
    println("    #####################")
    println("    #  Welcome to LINE  #")
    println("    #####################\n")
    prompt()
    Iterator.continually(readLine()).takeWhile(_ != null).foreach{
      command => command.toLowerCase match {
        case "c" =>
          println("\u001b[H\u001b[2J")
          // create user
        case "l" =>
          println("\u001b[H\u001b[2J")
          print(">> input user id: ")
          val id:String = readLine()
          login(id)
        case _ => println("!! Unknown operation.")
      }
        println("\u001b[H\u001b[2J")
        prompt()
    }
  }

  def login(id: String): Unit = {
    users.find(_.id == id) match {
      case Some(_) => println("OK")
      case None => println("!! no such user. login failed.")
    }
  }
}