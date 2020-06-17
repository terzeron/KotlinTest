package com.terzeron.grammar.grammar

fun sum(a: Int, b: Int): Int {
    return a + b
}

fun mul(a: Int, b: Int) = a * b

fun printSum(a: Int, b: Int): Unit {
    println("sum of $a and $b is ${a + b}")
}

fun printMul(a: Int, b: Int) {
    println("multiplication of $a and $b is ${a * b}")
}

fun printTest() {
    println(sum(1, 2))
    println(mul(3, 4))
    printSum(5, 6)
    printMul(7, 8)
}

fun varValTest() {
    val a1: Int = 1
    val b1 = 2 // inferred as int
    val c1: Int
    c1 = 3
    //c1 = 4 // compilation error: Val cannot be reassigned

    val pi = 3.14
    var x = 0
    fun incrementX() {
        x += 1
    }
    incrementX()
    println("x is $x")
}

fun conditionalExpressionTest() {
    fun maxOf(a: Int, b: Int): Int {
        if (a > b) {
            return a
        } else {
            return b
        }
    }

    fun minOf(a: Int, b: Int) = if (a < b) a else b

    println(maxOf(3, 4))
    println(minOf(15, 10))
}

fun nullCheckTest() {
    fun parseInt(str: String): Int? {
        if (str == "") {
            return null
        } else {
            return str.toInt()
        }
    }

    val arg1 = "3"
    val arg2 = "2"
    val x = parseInt(arg1)
    val y = parseInt(arg2)
    if (x != null && y != null) {
        println(x * y)
    } else {
        println("'$arg1' or '$arg2' is not a number")
    }
}

fun stringTemplateTest() {
    var a = 1
    val s1 = "a is $a"
    println(s1)
    a = 2
    val s2 = "${s1.replace("is", "was")}, but now is $a"
    println(s2)
}

fun typeCheckTest() {
    fun getStringLength(obj: Any): Int? {
        if (obj !is String) {
            return null
        }
        return obj.length
    }
    println(getStringLength("hello"))
    println(getStringLength(123))
}

fun forloopTest1() {
    val items = listOf("apple", "banana", "kiwi")
    for (item in items) {
        println(item)
    }
}

fun forloopTest2() {
    val items = listOf("apple", "banana", "kiwi")
    for (index in items.indices) {
        println(items[index])
    }

}

fun whileloopTest1() {
    val items = listOf("apple", "banana", "kiwi")
    var index = 0
    while (index < items.size) {
        println("item at $index is ${items[index]}")
        index++
    }
}

fun whenTest() {
    fun describe(obj: Any): String =
        when (obj) {
            1 -> "One"
            "Hello" -> "Greeting"
            is Long -> "Long"
            !is String -> "not a string"
            else -> "unknown"
        }

    println(describe(1))
    println(describe("Hello"))
    println(describe("music"))
}

fun rangeTest1() {
    val x = 10
    val y = 9
    if (x in 1..y + 1) {
        println("fits in range")
    }

    for (z in 1..5) {
        print("$z, ")
    }
    println()
    for (w in 1..10 step 2) {
        print("$w, ")
    }
    println()
    for (v in 9 downTo 0 step 3) {
        print("$v, ")
    }
    println()
}

fun rangeTest2() {
    val list = listOf("a", "b", "c")
    if (-1 !in 0..list.lastIndex) {
        println("-1 is out of range")
    }
    println("list lastIndex is ${list.lastIndex}")
    if (list.size !in list.indices) {
        println("list size is out of valid list indices range")
    }
    println("list size is ${list.size}")
    println("list indices is ${list.indices}")
}

fun collectionTest() {
    val fruits = listOf("banana", "avacado", "apple", "kiwi")
    fruits
        .filter { it.startsWith("a") }
        .sortedBy { it }
        .map { it.toUpperCase() }
        .forEach { println(it) }
}

fun main() {
    println("------ printTest ------")
    printTest()
    println("------ varValTest ------")
    varValTest()

    /*
    /* nested comment */
     */

    println("------ stringTemplateTest ------")
    stringTemplateTest()
    println("------ conditionalExpressionTest ------")
    conditionalExpressionTest()
    println("------ nullCheckTest ------")
    nullCheckTest()
    println("------ typeCheckTest ------")
    typeCheckTest()

    println("------ forloopTest1 ------")
    forloopTest1()
    println("------ forloopTest2 ------")
    forloopTest2()
    println("------ whileloopTest1 ------")
    whileloopTest1()
    println("------ whenTest ------")
    whenTest()

    println("------ rangeTest1 ------")
    rangeTest1()
    println("------ rangeTest2 ------")
    rangeTest2()
    println("------ collectionTest ------")
    collectionTest()
}

