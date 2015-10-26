package com.example

object Event{
  def prompt(): Unit = {
    println(">> type a command you want to exec.")
    print(">>( C:create user / L:login / Ctrl-D:exit ) : ")
  }
}
