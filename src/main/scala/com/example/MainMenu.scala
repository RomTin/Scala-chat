package com.example

object MainMenu {

  def prompt(): String = ">> type a command you want to execute\n-- A\t: add new friend\n-- L\t: show friend list\n-- Q\t: logout\n>> "
  def header(usr:User): Unit = {println(Event.clear()); Event.printUserInfo(usr)}

  def menu(ctrlUser:User): Unit = {
    // user start up and prompt
    header(ctrlUser)
    println(">> successfully logged in as " ++ ctrlUser.toString)
    print(prompt())

    // menu
    Iterator.continually(scala.io.StdIn.readLine()).takeWhile(_ != "q").foreach {
      command => command.toLowerCase match {

        // add new friend //////////////////////////////
        case "a" =>
          header(ctrlUser)
          println(">> type an phone-number or e-mail you want to add to friends")
          print(">>(number should be without hyphen) : ")

          val key = scala.io.StdIn.readLine()
          Event.addFriend(ctrlUser, key)
        ////////////////////////////////////////

        // preview friends list //////////////////////////////
        case "l" =>
          header(ctrlUser)
          println(">> === your friends")
          ctrlUser.friends.foreach(f => println(">> " ++ Event.findUser(f).toString()))
        ////////////////////////////////////////

        // error //////////////////////////////
        case _ => println("!! Unknown operation\n")
        ////////////////////////////////////////
      }
        // return to menu

        print(prompt())
    }
    // logout
    Event.logout()
  }

}