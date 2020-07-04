package com.scala.cookbook

object ScalaNumbers {

  def main(args: Array[String]): Unit = {

    /*
     ____________________________________________________
    | Data Type | Range                                  |
     ----------------------------------------------------
    | Char      | 16-bit unsigned Unicode character      |
    | Byte      | 8-bit signed value                     |
    | Short     | 16-bit signed value                    |
    | Int       | 32-bit signed value                    |
    | Long      | 64-bit signed value                    |
    | Float     | 32-bit IEEE 754 single precision float  |
    | Double    | 64-bit IEEE 754 single precision float  |
     ____________________________________________________
     */

    //Chapter2 Minimum and maximum values of Data Bytes

    //------------------------------------------
    println("Char Min Value : " + Char.MaxValue)
    println("Char Max Value : " + Char.MaxValue)

    //------------------------------------------
    println("Byte Min Value : " + Byte.MinValue)
    println("Byte Max Value : " + Byte.MaxValue)

    //--------------------------------------------
    println("Short Min Value : " + Short.MinValue)
    println("Short Max Value : " + Short.MaxValue)

    //----------------------------------------
    println("Int Min Value : " + Int.MinValue)
    println("Int Max Value : " + Int.MaxValue)

    //------------------------------------------
    println("Long Min Value : " + Long.MinValue)
    println("Long Max Value : " + Long.MaxValue)

    //--------------------------------------------
    println("Float Min Value : " + Float.MinValue)
    println("Float Max Value : " + Float.MaxValue)

    //----------------------------------------------
    println("Double Min Value : " + Double.MinValue)
    println("Double Max Value : " + Double.MaxValue)

    //Chapter2 Handling a base and radix
    println(Integer.parseInt("1", 2))
    println(Integer.parseInt("10", 2))
    println(Integer.parseInt("100", 2))
    println(Integer.parseInt("1", 8))
    println(Integer.parseInt("10", 8))

    println("100".toIntDigit(2))
    println("100".toIntDigit(8))

    println(toIntNo("1"))
    println(toIntNo("a"))
    println(toIntNo("1").getOrElse(0))
    println(toIntNo("a").getOrElse(0))

    val isNumber = toIntNo("1")
    isNumber match {
      case Some(n) => println("This is a number")
      case None => println("This is not a number")
    }

    println(1000L.isValidByte)
    println(10.isValidShort)

    println(0x20) // hex in int
    println(0x20L) // hex in long

    //Chapter2 Comparing floating points
    println(~=(1.2, 1.22, 0.1))
    println(~=(1.22, 2))

    println(1.7976931348623157E308 > Double.PositiveInfinity)

    //Chapter2 Generating Random Numbers
    val r = scala.util.Random
    println(r.nextInt)
    println(r.nextInt(100))
    println(r.nextFloat)

    val r1 = new scala.util.Random(100)
    r1.setSeed(1000L)
    r1.nextPrintableChar
    var range = 0 to r.nextInt(10)
    range = 0 to r.nextInt(10)
    println(range)

    for (i <- 0 to r.nextInt(10)) yield println(i * 2)

    //Chapter2 Creating a Range, List, or Array of Numbers
    val rang = 1 to 10
    val range1 = 1 to 10 by 2
    val range2 = 1 to 10 by 3
    println(rang)
    println(range1)
    println(range2)

    for (i <- 1 until 5) println(i)

    //Chapter2 Formatting Numbers and Currency
    val pi = scala.math.Pi
    println(f"$pi%1.5f")

    var formatter = java.text.NumberFormat.getIntegerInstance
    var money = formatter.format(1000000)
    println(money)

    val locale = new java.util.Locale("de", "DE")
    formatter = java.text.NumberFormat.getIntegerInstance(locale)

    money = formatter.format(1000000)
    println(money)

    val formatter1 = java.text.NumberFormat.getCurrencyInstance
    import java.util.{Currency, Locale}
    val in = Currency.getInstance(new Locale("en", "US"))
    println(in)
    formatter1.setCurrency(in)
    println(formatter1.format(1234567.123))

    val string = "\u20B9"
    println(string)

  }

  def ~=(x:Double, y:Double, precision:Double): Boolean = {
    if((x - y).abs < precision) true else false
  }
  def ~=(x:Double, y:Double): Boolean = {
    if((x-y).abs < 0.1) true else false
  }

  // if We will pass non digit value that will give number format exception
  // in that case it will return None otherwise it will return Some object with value converted to int
  def toIntNo(s : String):Option[Int] = {
    try {
      Some(s.toInt)
    } catch {
      case e: NumberFormatException => None
    }
  }

  implicit class StringToInt(s: String) {
    def toIntDigit(radix: Int): Int = Integer.parseInt(s, radix)
  }
}
