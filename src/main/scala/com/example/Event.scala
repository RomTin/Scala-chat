package com.example

object Event{

  def createUser(): User = {
    println(">> type user information")
    print("-- User ID\t: ")
    val id = scala.io.StdIn.readLine()
    print("-- User name\t: ")
    val name = scala.io.StdIn.readLine()
    print("-- TEL num\t: ")
    val phone = scala.io.StdIn.readLine()
    print("-- Mail addr\t: ")
    val addr = scala.io.StdIn.readLine()

    new User(id, name, phone, addr)
  }

   def existUser(id: String): Boolean = {
     Main.users.find(_.id == id) match {
       case Some(_) => true
       case None => false
     }
   }

  def findUser(id: String): User = {
    Main.users.find(_.id == id) match {
      case Some(user) => user
      case None => null
    }
  }

  // TODO
  def addFriend(): Unit = {}
  def previewFriends(): Unit = {}
  def sendMessage(): Unit = {}
  def connectRoom(): Unit = {}
  def writeLog(): Unit = {}

  def printUserInfo(usr:User): Unit = {
    println("|| === User Information ===")
    println("|| Name\t: " ++ usr.name)
    println("|| ID\t: " ++ usr.id)
    println("|| Phone\t: " ++ usr.phone)
    println("|| Addr\t: " ++ usr.addr)
    println()
  }

  def exit(): Unit = {
    println(Main.clear())
    println("  ## ScalaChat has been closed. Bye! ##\n")
  }

  def logout(): Unit = {
    println(Main.clear())
    println("  ## logout successfully. ##\n")
  }
}
