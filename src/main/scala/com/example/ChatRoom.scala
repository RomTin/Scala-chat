package com.example

import java.util.Date
import scala.collection.mutable.ListBuffer

class ChatRoom(val id1: String, val id2: String) {
  val msg: ListBuffer[Message] = ListBuffer(new Message("administrator", "Let's enjoy chat!!", ""))
  def prompt(): String = "\n>> type a message or \":q!\" to quit the room\n>> "

  def chatMain(from:String, to:String): Unit = {
    // enter and initialize
    msg.foreach(m => if (m.id == to) m.read = 1)
    Event.previewMessage(msg)
    print(prompt())

    Iterator.continually(scala.io.StdIn.readLine()).takeWhile(_ != ":q!").foreach {
      input =>
        sendMessage(from, input)
        println(Event.clear())
        Event.previewMessage(msg)
        print("\n" ++ prompt())
    }
  }

  def sendMessage(from:String, body:String): Unit = {
    msg += new Message(from, body, "%tT" format new Date)
  }

}

class Message(val id:String, val body:String, val date:String){
  var read = 0
  def readFlag(): String = if(read == 1){ " (read)" }else{ "" }

  override def toString = "|| " ++ id ++ "\t|| " ++ date ++ readFlag() ++ "\n|| " ++ body ++ "\n========================================"
}