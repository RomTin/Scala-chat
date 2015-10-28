package com.example

class User(val id:String, val name:String, val phone:String, val addr:String){
  // val friends:List[String]

  //def userMenu()
  //def displayAllRooms()
  //def connectRoom()
  //def addNewFriend()
  //def logout()
  override def toString() = name + " (id: " + id + ") "
}