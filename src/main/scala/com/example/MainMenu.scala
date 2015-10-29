package com.example

object MainMenu {
  def prompt(): String = ">> type a command you want to execute.\n-- A\t: add new friend\n-- L\t: show friend list\n-- Q\t: logout\n>> "
  def menu(ctrlUser:User): Unit = {
    println(Main.clear())
    Event.printUserInfo(ctrlUser)
    println(">> successfully logged in as " ++ ctrlUser.toString ++ "\n")
    print(prompt())

    // menu
    Iterator.continually(scala.io.StdIn.readLine()).takeWhile(_ != null).foreach {
      //  command => command.toLowerCase match{
      println(_)
        // add new friend

    //  }
    }
    // logout
    Event.logout()
  }
}