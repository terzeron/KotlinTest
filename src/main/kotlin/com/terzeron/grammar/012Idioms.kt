package com.terzeron.grammar

import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

fun dtoTest() {
    data class Customer(val name: String, val email: String)
    // getter & setter
    // equals(), hashCode(), toString(), copy(), component1(), component2(), ...
    val customer = Customer("john", "john@mail.com")
    println(customer)
    println(customer.toString())
    println(customer.name)
    println(customer.email)
    println(customer.component1())

    // equality
    println(customer.equals(customer))
    val customer2 = Customer("john", "john@mail.com")
    println(customer.equals(customer2))
    val customer3 = Customer("mike", "mike@mail.com")
    println(customer.equals(customer3))
    val customer4 = customer.copy("john", "john@mail.com")
    println(customer.equals(customer4))
    val customer5 = customer.copy("mike", "mike@mail.com")
    println(customer.equals(customer5))

}

fun defaultParameterTest() {
    testFunc1()
    testFunc1(a = 4)
    testFunc1(3, "hello")
}

fun testFunc1(a: Int = 0, b: String = "") {
    println("a: $a, b: $b")
}

fun filterTest() {
    val list = listOf(-2, -1, 0, 1, 2)
    val positives = list.filter { x -> x > 0 }
    println("list: $list")
    println("positives: $positives")
    val negatives = list.filter { it < 0 }
    println("negatives: $negatives")
}

fun elementPresenceTest() {
    val list = listOf("mike", "john", "bob")
    if ("john" in list) {
        println("john exists in $list")
    }
    if ("bill" !in list) {
        println("bill doesn't exist in $list")
    }
}

data class Foo(val a: Int)
data class Bar(val a: Int)
data class Music(val a: Int)

fun instanceTest() {
    val x = Foo(1)
    val y = Bar(2)
    val z = Music(3)
    testFunc2(x)
    testFunc2(y)
    testFunc2(z)
}

fun testFunc2(x: Any) {
    when (x) {
        is Foo -> println("parameter is Foo")
        is Bar -> println("parameter is Bar")
        else -> println("parameter is unknown")
    }
}

fun collectionTraverseTest() {
    val map = mapOf("a" to 1, "c" to 3, "b" to 2)
    for ((k, v) in map) {
        println("$k -> $v")
    }
}

fun rangeTest() {
    for (i in 1..10) {
        print("$i ")
    }
    println()
    for (i in 5 until 10) {
        print("$i ")
    }
    println()
    for (x in 2..10 step 2) {
        print("$x ")
    }
    println()
    val x = 3
    if (x in 1..10) {
        print("x in 1..10")
    }
    println()
}

fun readonlyTest() {
    val list = listOf("a", "b", "c")
    val map1 = mapOf("a" to 1, "b" to 2, "c" to 3)
    val map2 = mutableMapOf("k1" to 1, "k2" to 2, "k3" to 3)
    println(list)
    println(map1["a"])
    // compilation error - no set method provising array access
    //map1["d"] = 4
    println(map2["k2"])
    map2["k2"] = 22
    map2["k4"] = 44
    println(map2)
}

fun getName(): String {
    return "mike"
}

fun lazyTest() {
    val p1: String by lazy {
        "john"
    }
    println(p1)
    val p2: String by lazy {
        getName()
    }
    println("before accessing p2")
    println(p2)
    println("after accessing p2")
}

fun extensionTest() {
    // trait???
    fun String.spaceToCamelCase(): String {
        val chars = this.split("")
        var doConvertToUppercase: Boolean = false
        val resultList: MutableList<String> = mutableListOf()
        var newChar: String = ""
        for (char in chars) {
            if (char == " ") {
                doConvertToUppercase = true
                continue
            } else if (char == "") {
                continue
            } else {
                if (doConvertToUppercase) {
                    newChar = char.toUpperCase()
                    doConvertToUppercase = false
                } else {
                    newChar = char
                }
            }
            resultList.add(newChar)
        }
        return resultList.joinToString("")
    }

    println("hello world java music".spaceToCamelCase())
}

object Resource {
    val name = "Name"
}

fun singletonTest() {
    val r = Resource
    println(Resource.name)
}

fun nullHandlingTest() {
    val files = File("Test").listFiles()
    println(files?.size)
    println(files?.size ?: "empty")

    val values = mapOf("address" to "john@mail.com", "name" to "John")
    try {
        val email = values["email"] ?: throw IllegalStateException("Email is missing!") // exception!
        println(email)
    } catch (e: Exception) {
        e.printStackTrace()
    }

    val emails: List<String> = listOf()
    val mainEmail = emails.firstOrNull() ?: ""
    println("mailEmail: $mainEmail")

    fun valueTest(value: String?) {
        value?.let {
            println("value is not null")
        }
    }

    val value = "test data"
    valueTest(value)
}

fun returnOnWhenTest() {
    fun transform(color: String): Int {
        return when (color) {
            "Red" -> 0
            "Green" -> 1
            "Blue" -> 2
            else -> throw IllegalArgumentException("Invalid color param value")
        }
    }

    println(transform("Red"))
    val result = try {
        transform("Yellow")
    } catch (e: IllegalArgumentException) {
        -1
    }
    println(result)
}

fun ifExpressionTest() {
    fun foo(param: Int): String {
        return if (param == 1) {
            "one"
        } else {
            "many"
        }
    }
    println(foo(1))
    println(foo(4))
}

fun builderStyleMethodTest() {
    fun arrayOfMinusOnes(size: Int): IntArray {
        return IntArray(size).apply { fill(-1) }
    }

    val arr: IntArray = arrayOfMinusOnes(5)
    for (item in arr) {
        print("$item ")
    }
    println()
}

fun singleExpressionFunctionTest() {
    fun theAnswer() = 42
    fun transform(color: String): Int = when (color) {
        "Red" -> 1
        "Green" -> 2
        "Blue" -> 3
        else -> 4
    }

    println(theAnswer())
    println(transform("Red"))
}

fun withExpressionTest() {
    class Turtle {
        fun penDown() {
            println("pen down")
        }

        fun penUp() {
            println("pen up")
        }

        fun turn(degrees: Double) {
            println("turn $degrees degree")
        }

        fun forward(pixels: Double) {
            println("forward $pixels pixel")
        }
    }

    val myTurtle = Turtle()
    // multiple methods calling
    with(myTurtle) {
        penDown()
        for (i in 1..3) {
            forward(100.0)
            turn(90.0)
        }
        penUp()
    }
}

fun tryWithResourcesTest() {
    val stream = Files.newInputStream(Paths.get("test.txt"))
    stream.buffered().reader().use { reader ->
        println(reader.readText())
    }
}

fun nullableBooleanTest() {
    fun booleanTest(b: Boolean?): String {
        return if (b == true) {
            "true"
        } else {
            "unknown (false or null)"
        }
    }

    var b: Boolean? = true
    println(booleanTest(b))
    b = null
    println(booleanTest(b))
    b = false
    println(booleanTest(b))
}

fun variableSwapTest() {
    var a = 1
    var b = 2
    a = b.also { b = a }
    println("a is $a, b is $b")
}

fun main() {
    println("------ dtoTest ------")
    dtoTest()
    println("------ defaultParameterTest ------")
    defaultParameterTest()
    println("------ filterTest ------")
    filterTest()
    println("------ elementPresenceTest ------ ")
    elementPresenceTest()
    println("------ instanceTest ------")
    instanceTest()
    println("------ coellectionTraverseTest ------")
    collectionTraverseTest()
    println("------ rangeTest ------")
    rangeTest()
    println("------ readonlyTest ------")
    readonlyTest()
    println("------ lazyTest ------")
    lazyTest()
    println("------ extensionTest ------")
    extensionTest()
    println("------ singletonTest ------")
    singletonTest()
    println("------ notNullShorthandTest ------")
    nullHandlingTest()
    println("------ returnOnWhenTest ------")
    returnOnWhenTest()
    println("------ ifExpressionTest ------")
    ifExpressionTest()
    println("------ builderStyleMethodTest ------")
    builderStyleMethodTest()
    println("------ singleExpressionFunctionTest ------")
    singleExpressionFunctionTest()
    println("------ withExpressionTest ------")
    withExpressionTest()
    println("------ tryWithResourcesTest ------")
    tryWithResourcesTest()
    println("------ nullableBooleanTest ------")
    nullableBooleanTest()
    println("------ variableSwapTest ------")
    variableSwapTest()

}

