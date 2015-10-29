package com.example

import scala.collection.mutable.ListBuffer

object Main {

  val users: ListBuffer[User] = ListBuffer(new User("id_test", "first_created", "0120828828", "apple_and_google@yahoo.co.jp"))
  def prompt(): String = ">> type a command you want to execute\n-- C\t\t: create user\n-- L\t\t: login\n-- Ctrl-D\t: exit\n>> "
  def clear(): String = "\u001b[H\u001b[2J"
  def startUp(): String = "    ##########################\n    #  Welcome to ScalaChat  #\n    ##########################\n"

  def main(args: Array[String]): Unit = {
    // start up and prompt
    println(clear())
    println(startUp())
    print(prompt())

    // main
    Iterator.continually(scala.io.StdIn.readLine()).takeWhile(_ != null).foreach {
      command => command.toLowerCase match {

        // user creation //////////////////////////////
        case "c" =>
          println(clear())
          val newUser = Event.createUser()
          println(
            if (Event.existUser(newUser.id))
              "!! UserID duplicated\n"
            else {
              // TODO input validation
              // id a-z _
              // tel 0-9
              users += newUser
              ">> created successfully\n"
            }
          )
          // test print
          users.foreach(println(_))
        ////////////////////////////////////////

        // login //////////////////////////////
        case "l" =>
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
        ////////////////////////////////////////

        // error //////////////////////////////
        case _ => println("!! Unknown operation\n")
        ////////////////////////////////////////
      }
        // return to main
        print(prompt())
    }
    // exit program
    Event.exit()
  }

}