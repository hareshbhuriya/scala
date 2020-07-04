package com.scala.cookbook
import java.io.{FileInputStream, FileOutputStream, IOException}

import scala.annotation.switch

object ControlStructures {

  def main(args: Array[String]): Unit = {

    //Chapter3 Various ways of for loops
    val a = Array("apple", "banana", "orange")

    for (i <- a.indices) {
      println(s"$i is ${a(i)}")
    }

    for ((e, count) <- a.zipWithIndex) {
      println(s"$count is $e")
    }

    for(i <- 1 to 10 if i < 4) {
      println(i)
    }

    //Chapter3 Looping over Map
    val names = Map("fname" -> "Robert", "lname" -> "Goren")
    for((k, v) <- names) println(s"key: $k, value: $v")

    //for/yield works like map method
    //it returns collection
    a.foreach(e => println(e.toUpperCase()))

    //Chapter3 Using for Loops with Multiple Counters
    for (i <- 1 to 2; j <- 1 to 2) println(s"i = $i, j = $j")

    val array = Array.ofDim[Int](2,2)
    array(0)(0) = 0
    array(0)(1) = 1
    array(1)(0) = 2
    array(1)(1) = 3

    for {
      i <- 0 to 1
      j <- 0 to 1
    } println(s"($i)($j) = ${array(i)(j)}")

    val fruits = "apple" :: "banana" :: "orange" :: Nil
    val out = for (e <- fruits) yield e.toUpperCase //returns list

    //Chapter3 Implementing break and continue

    //Chapter3 If you don't like break and continue

    //Chapter3 Using the if Construct Like a Ternary Operator

    //Chapter3 Using a Match Expression Like a switch Statement

    //Chapter3 The @switch annotation
    val i = 3
    val x = (i: @switch) match {
      case 1 => "One"
      case 2 => "Two"
      case _ => "Other"
    }
    println(x)

    //Chapter3 Matching Multiple Conditions with One Case Statement

    //Chapter3 Assigning the Result of a Match Expression to a Variable
    val someNumber = 5
    val evenOrOdd = someNumber match {
      case 1 | 3 | 5 | 7 | 9 => "odd"
      case 2 | 4 | 6 | 8 | 10 => "even"
    }
    println(s"$someNumber is $evenOrOdd")

    //Chapter3 Accessing the Value of the Default Case in a Match Expression
    i match {
      case 0 => println("1")
      case 1 => println("2")
      case default => println("You gave me: " + default)
    }

    //Chapter3.11 Using Pattern Matching in Match Expressions
    def echoWhatYouGaveMe(x: Any): String = x match {
      // constant patterns
      case 0 => "zero"
      case true => "true"
      case "hello" => "you said 'hello'"
      case Nil => "an empty List"

      // sequence patterns
      case List(0, _, _) => "a three-element list with 0 as the first element"
      case List(1, _*) => "a list beginning with 1, having any number of elements"
      case Vector(1, _*) => "a vector starting with 1, having any number of elements"

      // tuples
      case (a, b) => s"got $a and $b"
      case (a, b, c) => s"got $a, $b, and $c"

      // constructor patterns
      case Person(first, "Alexander") => s"found an Alexander, first name = $first"
      case Dog("Suka") => "found a dog named Suka"

      // typed patterns
      case s: String => s"you gave me this string: $s"
      case i: Int => s"thanks for the int: $i"
      case f: Float => s"thanks for the float: $f"
      case a: Array[Int] => s"an array of int: ${a.mkString(",")}"
      case as: Array[String] => s"an array of strings: ${as.mkString(",")}"
      case d: Dog => s"dog: ${d.name}"
      case list: List[_] => s"thanks for the List: $list"
      case m: Map[_, _] => m.toString

      // the default wildcard pattern
      case _ => "Unknown"
    }
    // trigger the constant patterns
    println(echoWhatYouGaveMe(0))
    println(echoWhatYouGaveMe(true))
    println(echoWhatYouGaveMe("hello"))
    println(echoWhatYouGaveMe(Nil))

    // trigger the sequence patterns
    println(echoWhatYouGaveMe(List(0,1,2)))
    println(echoWhatYouGaveMe(List(1,2)))
    println(echoWhatYouGaveMe(List(1,2,3)))
    println(echoWhatYouGaveMe(Vector(1,2,3)))

    // trigger the tuple patterns
    println(echoWhatYouGaveMe((1,2))) // two element tuple
    println(echoWhatYouGaveMe((1,2,3))) // three element tuple

    // trigger the constructor patterns
    println(echoWhatYouGaveMe(Person("Melissa", "Alexander")))
    println(echoWhatYouGaveMe(Dog("Suka")))

    // trigger the typed patterns
    println(echoWhatYouGaveMe("Hello, world"))
    println(echoWhatYouGaveMe(42))
    println(echoWhatYouGaveMe(42F))
    println(echoWhatYouGaveMe(Array(1,2,3)))
    println(echoWhatYouGaveMe(Array("coffee", "apple pie")))
    println(echoWhatYouGaveMe(Dog("Fido")))
    println(echoWhatYouGaveMe(List("apple", "banana")))
    println(echoWhatYouGaveMe(Map(1->"Al", 2->"Alexander")))

    // trigger the wildcard pattern
    println(echoWhatYouGaveMe("33d"))

    //Chapter3.12. Using Case Classes in Match Expressions

    //Chapter3.13. Adding if Expressions (Guards) to Case Statements

    //Chapter3.14. Using a Match Expression Instead of isInstanceOf

    //Chapter3.15. Working with a List in a Match Expression
    val num = List(1, 2, 3)
    val y = 1 :: 2 :: 3 :: Nil

    def listToString(list: List[String]): String = list match {
      case s :: rest => s + " " + listToString(rest)
      case Nil => ""
    }

    println(listToString(fruits))

    //Chapter3.16. Matching One or More Exceptions with try/catch
    val s = "Foo"
    try {
      val i = s.toInt
    } catch {
      case _: Throwable => println("exception ignored")
    }

    def toInt(s: String): Option[Int] =
      try {
        Some(s.toInt)
      } catch {
        case e: Exception => throw e
      }

    //Chapter3.17. Declaring a Variable Before Using It in a try/catch/finally Block
    var in = None: Option[FileInputStream]
    var fout = None: Option[FileOutputStream]
    try {
      in = Some(new FileInputStream("/tmp/Test.class"))
      fout = Some(new FileOutputStream("/tmp/Test.class.copy"))
      var c = 0
      while ({c = in.get.read; c != -1}) {
        fout.get.write(c)
      }
    } catch {
      case e: IOException => e.printStackTrace
    } finally {
      println("entered finally ...")
      if (in.isDefined) in.get.close
      if (fout.isDefined) fout.get.close
    }
  }
  
}

case class Person(firstName: String, lastName: String)

case class Dog(name: String)