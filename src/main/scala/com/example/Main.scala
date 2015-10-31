package com.example

import scala.collection.mutable.ListBuffer

object Main {

  val users: ListBuffer[User] = ListBuffer(new User("id_test", "first_created", "0120828828", "apple_and_google@yahoo.co.jp"), new User("id_test2", "second_created", "0120114514", "softbank_docomo@ezweb.ne.jp"))
  val rooms: ListBuffer[ChatRoom] = ListBuffer(new ChatRoom("id_test", "id_test2"))
  def prompt(): String = ">> type a command you want to execute\n-- C\t\t: create user\n-- L\t\t: login\n-- Ctrl-D\t: exit\n>> "

  def main(args: Array[String]): Unit = {
    // start up and prompt
    println(Event.clear())
    println(Event.startUp())
    print(prompt())

    // main
    Iterator.continually(scala.io.StdIn.readLine()).takeWhile(_ != null).foreach {
      command => command.toLowerCase match {
        case "c" => Event.createUser()
          users.foreach(println(_))
        case "l" => Event.login()
        case  _  => println("!! Unknown operation\n")
      }
        // return to main
        print(prompt())
    }
    // exit program
    Event.exit()
  }

}