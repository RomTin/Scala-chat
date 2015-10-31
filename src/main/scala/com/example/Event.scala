package com.example

import scala.collection.mutable.ListBuffer

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
            findUser(key).friends += usr.id
            ">> \"" + key + "\" is successfully added to friends list\n"
        }
      } else {
        "!! no such user\n"
      }
    )
  }
  def showFriends(usr:User): Unit = {
    println(">> === your friends")
    usr.friends.foreach(
      f =>
        println("[[ " ++ Event.findUser(f).toString() ++ " ]]" ++ remainedUnRead(findRoom(usr.id, f), f))
    )
    println()
  }
  def existFriend(usr:User, id:String): Boolean = {
    usr.friends.find(_ == id) match {
      case Some(_) => true
      case None => false
    }
  }
  def previewMessage(msg:ListBuffer[Message]): Unit = {
    println(clear())
    msg.foreach(println(_))
  }
  def remainedUnRead(room:ChatRoom, id:String): String = {
    room.msg.find( m => m.id == id && m.read == 0 ) match {
      case Some(_) => " <<New Message!>>"
      case None => ""
    }
  }
  def roomEntrance(usr:User): Unit = {
    print(">> type a User ID you want to send messages, or \":q!\" to abort\n>> ")
    val input = scala.io.StdIn.readLine()
    input match {
      case ":q!" => println()
      case _ =>
        existFriend(usr, input) match {
          case true  => connectRoom(usr.id, input)
          case false => println("!! no such friend\n")
        }
    }
  }
  def findRoom(id1: String, id2: String): ChatRoom = {
    Main.rooms.find(r => Set(r.id1, r.id2) == Set(id1, id2) ) match {
      case Some(chatRoom) => chatRoom
      case None =>
        val newRoom = new ChatRoom(id1, id2)
        Main.rooms += newRoom
        newRoom
    }
  }
  def connectRoom(from:String, to:String): Unit = {
    val room = findRoom(from, to)
    room.chatMain(from, to)
  }
  // TODO
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
    Event.existUser(id) match {
      case true => MainMenu.menu(Event.findUser(id))
      case false => println("!! no such user, failed to login\n")
    }
  }
  def logout(): Unit = {
    println(clear())
    println("  ## logout successfully ##\n")
  }
}
