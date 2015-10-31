package com.example

import scala.collection.mutable.ListBuffer

class ChatRoom(val id1: String, val id2: String) {
  val msg: ListBuffer[Message] = ListBuffer(new Message("administrator", "Let's enjoy chat!!", ""))
}

class Message(val id:String, val body:String, val date:String){
  var checked = 0
  override def toString = "==============================\n" ++ "|| " ++ id ++ "\t|| " ++ body ++ "\n|| " ++ date ++ "\n==============================\n"
}