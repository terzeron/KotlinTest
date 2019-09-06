package com.terzeron.grammar

import java.lang.Integer.parseInt

fun ifTest() {
    val a = 3
    val b = 4
    var max: Int

    if (a > b) {
        max = a
    } else {
        max = b
    }
    println("max=$max")

    val max2 = if (a < b) a else b
    println("max2=$max2")
}

fun whenTest1() {
    var x = 2
    when (x) {
        1 -> println("x==1")
        2 -> println("x==2")
        else -> println("unknown")
    }

    when (x) {
        0, 1 -> println("x is 0 or 1")
        else -> println("otherwise")
    }

    x = 5
    val s = "5"
    when (x) {
        parseInt(s) -> println("s encodes x")
        else -> println("s does not encode x")
    }

    x = 30
    val validNumbers = 0..100
    when (x) {
        in 1..10 -> println("x is in the range")
        in validNumbers -> println("x is valid")
        !in 10..20 -> println("x is outside the range")
        else -> print("unknown")
    }
}

fun whenTest3() {
    fun Int.isOdd(): Boolean {
        return (this % 2 == 1)
    }

    fun Int.isEven(): Boolean {
        return (this % 2 == 0)
    }

    // without parameter
    val x: Int = 4
    when {
        x.isOdd() -> println("x is odd")
        x.isEven() -> println("x is even")
        else -> print("x is unknown")
    }
}

fun whenTest2() {
    fun hasPrefix(x: Any) = when (x) {
        is String -> x.startsWith("prefix")
        else -> false
    }

    println(hasPrefix("prefixTestString"))
}

fun main() {
    ifTest()
    whenTest1()
    whenTest2()
    whenTest3()
}