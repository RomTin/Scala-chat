package com.example

import scala.collection.mutable.ListBuffer

class User(val id:String, val name:String, val phone:String, val addr:String){
  val friends:ListBuffer[String] = ListBuffer.empty
  override def toString = name + " (id: " + id + ")"
}