package com.terzeron.grammar

import java.lang.IllegalArgumentException

fun simpleTypesTest() {
    val one = 1
    val threeBillion = 3000000000
    val oneLong = 1L
    val oneByte: Byte = 1

    val pi = 3.14
    val e = 2.718
    val eFloat = 2.718f

    fun printDouble(d: Double) {
        println(d)
    }

    val i = 1
    val d = 1.1
    val f = 1.1f
    printDouble(d)
    //printDouble(i) // error - type mismatch
    //printDouble(f)

    // 언더스코어 사용 가능
    val oneMillion = 1_000_000
    val creditCardNumber = 1234_5678_9012_3456L
    val socialSecurityNumber = 999_99_9999L
    val hexBytes = 0xFF_EC_DE_5E
    val bytes = 0b11010010_01101001_10010100_10010010
}

fun boxingIdentityTest() {
    val a: Int = 10000
    println(a == a)
    // boxing
    val boxedA: Int? = a
    val anotherBoxedA: Int? = a
    println(boxedA === anotherBoxedA)
    println(boxedA == anotherBoxedA)
}

fun explicitConversionTest() {
    val a: Int? = 1
    //val b: Long? = a // error - type mismatch
    //print(b == a) // error - operator cannot be applied to

    val b: Byte = 1
    //val i: Int = b // error - type mismatch

    val i: Int = b.toInt()
    println(i)

    val l = 1L + 3
}

fun shiftTest() {
    // shift left
    val x = (1 shl 2) and 0x000FF000
}

fun characterTest() {
    val c: Char = '3'
    //if (c == 39) { } // error - incompatible types
    if (c == '1') { }

    fun decimalDigitValue(c: Char): Int {
        if (c !in '0'..'9')
            throw IllegalArgumentException("out of range")
        return c.toInt() - '0'.toInt()
    }
}

fun arrayTest() {
    val asc = Array(5) { i -> (i * i).toString() }
    asc.forEach { print("$it ") }
    println()

    val x: IntArray = intArrayOf(1, 2, 3)
    x.forEach { print("$it ")}
    println()
    x[0] = x[1] + x[2]
    x.forEach { print("$it ") }
    println()

    val arr1 = IntArray(5) // 5개 0
    arr1.forEach { print("$it ")}
    println()
    val arr2 = IntArray(5, { 42 }) // 5개 42
    arr2.forEach { print("$it ")}
    println()
    val arr3 = IntArray(5, { it * 2 }) // 5개 0 2 4 6 8
    arr3.forEach { print("$it ")}
    println()
}

fun literalTest() {
    val b: UByte = 1u // unsigned
    val s: UShort = 1u
    val l: ULong = 1u

    val a1 = 42u
    val a2 = 0xFFFF_FFFF_FFFFu

    val a = 1UL // unsigned long
}

fun stringTest() {
    val s = "hello world"
    for (c in s)
        print("$c ")
    println()

    val str = "abc" + 1
    println(str + "def")

    val text1 = """
        for (c in "foo")
            print(c)
    """
    val text2 = """
        for (c in "foo")
            print(c)
    """.trimIndent()
    println(text1)
    println(text2)
}

fun templateTest() {
    val i = 10
    println("i = $i")

    val s = "abc"
    println("$s.length is ${s.length}")

    val price = """
        ${'$'}9.99 
    """
    println(price)
}

fun main() {
    println("------ simpleTypesTest ------")
    simpleTypesTest()
    println("------ boxingIdentityTest ------")
    boxingIdentityTest()
    println("------ boxingIdentityTest ------")
    explicitConversionTest()
    println("------ shiftTest ------")
    shiftTest()
    println("------ characterTest ------")
    characterTest()
    println("------ arrayTest ------")
    arrayTest()
    println("------ literalTest ------")
    literalTest()
    println("------ stringTest ------")
    stringTest()
    println("------ templateTest ------")
    templateTest()
}

