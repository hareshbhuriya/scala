  package com.scala.cookbook

  object ScalaString {

    def main(args: Array[String]): Unit = {
      println("Scala Strings")

      //Chapter1 Replacing Patterns in Strings

      val address = "123 Main Street".replaceAll("[0-9]", "x")
      println(address)

      val regex = "[0-9]".r
      val newAddress = regex.replaceAllIn("123 Main Street", "x")
      println(newAddress)
      val result = "123".replaceFirst("[0-9]", "x")
      println(result)

      val pattern = "([0-9]+) ([A-Za-z]+)".r
      val pattern(count, fruit) = "100 Bananas"

      println(count)
      println(fruit)

      //Chapter1 Extracting Parts of Strings that matches pattern

      // match "movies 80301"
      val MoviesZipRE = "movies (\\d{5})".r

      // match "movies near boulder, co"
      val MoviesNearCityStateRE = "movies near ([a-z]+), ([a-z]{2})".r

      "movies near something, so" match {
        case MoviesZipRE(zip) => println(zip)
        case MoviesNearCityStateRE(city, state) => println(city, state)
        case _ => println("did not match a regex")
      }

      //Chapter1 Extracting character in a string
      println("hello"(1))
      println("hello".apply(1))

      //Chapter1 Adding own method to the String class
      println("HAL".increment)
      println("HAL".decrement)
      println("haresh".hideAll)
    }

    implicit class StringImprovements(s: String) {
      def increment: String = s.map(c => (c + 1).toChar)
      def decrement: String = s.map(c => (c - 1).toChar)
      def hideAll:String = s.replaceAll(".", "*")
      def plusOne: Int = s.toInt + 1
      def asBoolean: Boolean = s match {
        case "0" | "zero" | "" | " " => false
        case _ => true
      }
    }

  }
  
  package object utils {
    implicit class StringImprovements(val s: String) {
      def incrementChars: String = s.map(c => (c + 1).toChar)
    }
  }