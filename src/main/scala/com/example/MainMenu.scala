package com.example

object MainMenu {

  def prompt(): String = ">> type a command you want to execute\n-- A\t: add new friend\n-- L\t: show friend list\n-- Q\t: logout\n>> "
  def clear(): String = "\u001b[H\u001b[2J"

  def menu(ctrlUser:User): Unit = {
    // user start up and prompt
    println(Main.clear())
    Event.printUserInfo(ctrlUser)
    println(">> successfully logged in as " ++ ctrlUser.toString)
    print(prompt())

    // menu
    Iterator.continually(scala.io.StdIn.readLine()).takeWhile(_ != "q").foreach {
      command => command.toLowerCase match {

        // add new friend //////////////////////////////
        case "a" =>
          println(clear())
          println(">> type an phone-number or e-mail you want to add to friends")
          print(">>(number should be without hyphen) : ")
          val key = scala.io.StdIn.readLine()
          println(
            if (Event.existUserByPhoneOrAddr(key)) {
              ctrlUser.friends += key
              ">> " + key + " is successfully added to friends list\n"
            } else {
              "!! no such user"
            }
          )
        ////////////////////////////////////////

        // preview friends list //////////////////////////////
        case "l" => print(_)
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