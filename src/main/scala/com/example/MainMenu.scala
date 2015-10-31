package com.example

object MainMenu {

  def prompt(): String = ">> type a command you want to execute\n-- A\t: add new friend\n-- L\t: show friend list\n-- Q\t: logout\n>> "
  def header(usr:User): Unit = {println(Event.clear()); Event.printUserInfo(usr)}

  def menu(ctrlUser:User): Unit = {
    // user start up and prompt
    header(ctrlUser)
    println(">> successfully logged in as \"" ++ ctrlUser.toString ++ "\"")
    print(prompt())

    // menu
    Iterator.continually(scala.io.StdIn.readLine()).takeWhile(_ != "q").foreach {
      command =>
        header(ctrlUser)
        command.toLowerCase match {
        case "a" => Event.addFriend(ctrlUser)
        case "l" => Event.showFriends(ctrlUser); Event.roomEntrance(ctrlUser)
        case  _  => println("!! Unknown operation\n")
      }
        // return to menu
        print(prompt())
    }
    // logout
    Event.logout()
  }

}