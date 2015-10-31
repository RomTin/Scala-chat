package com.example

object Event{

  def clear(): String = "\u001b[H\u001b[2J"
  def startUp(): String = "    ##########################\n    #  Welcome to ScalaChat  #\n    ##########################\n"

  // TODO input validation
  def createUser(): Unit = {
    println(Event.clear())
    println(">> type user information")
    print("-- User ID\t: ")
    val id = scala.io.StdIn.readLine()
    print("-- User name\t: ")
    val name = scala.io.StdIn.readLine()
    print("-- TEL num\t: ")
    val phone = scala.io.StdIn.readLine()
    print("-- Mail addr\t: ")
    val addr = scala.io.StdIn.readLine()

    val newUser = new User(id, name, phone, addr)

    println(
      if (existUser(newUser.id))
        "!! UserID duplicated\n"
      else {
        // id a-z _
        // tel 0-9
        Main.users += newUser
        ">> created successfully\n"
      }
    )
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
  def existUserByPhoneOrAddr(id: String): Boolean = {
   true
  }

  def addFriend(usr:User): Unit  = {
    println(">> type an phone-number or e-mail you want to add to friends")
    print(">>(number should be without hyphen) : ")
    val key = scala.io.StdIn.readLine()

    println(
      if (existUserByPhoneOrAddr(key)) {
        existFriend(usr, key) match {
          case true =>
            ">> already added to your friends\n"
          case false =>
            usr.friends += key
            ">> \"" + key + "\" is successfully added to friends list\n"
        }
      } else {
        "!! no such user\n"
      }
    )
  }

  def showFriends(usr:User): Unit = {
    println(">> === your friends")
    usr.friends.foreach(f => println("[[ " ++ Event.findUser(f).toString() ++ " ]]"))
    println()
  }

  def existFriend(usr:User, id:String): Boolean = {
    usr.friends.find(_ == id) match {
      case Some(_) => true
      case None => false
    }
  }

  // TODO
  def sendMessage(): Unit = {}
  def previewMessage(): Unit = {}

  def roomEntrance(): Unit = {

  }

  // TODO
  def findRoom(): Unit = {}
  def connectRoom(): Unit = {}
  def writeLog(): Unit = {}

  def printUserInfo(usr:User): Unit = {
    println("|| === User Information ===")
    println("|| Name\t: " ++ usr.name)
    println("|| ID\t: " ++ usr.id)
    println("|| TEL\t: " ++ usr.phone)
    println("|| Addr\t: " ++ usr.addr)
    println("|| ========================")
    println()
  }

  def exit(): Unit = {
    println(clear())
    println("  ## ScalaChat has been closed, Bye! ##\n")
  }

  def login(): Unit = {
    println(clear())
    print(">> input user id: ")
    val id: String = scala.io.StdIn.readLine()
    println(
      if (Event.existUser(id)) {
        MainMenu.menu(Event.findUser(id))
        ""
      }
      else
        "!! no such user, failed to login\n"
    )
  }

  def logout(): Unit = {
    println(clear())
    println("  ## logout successfully ##\n")
  }
}
