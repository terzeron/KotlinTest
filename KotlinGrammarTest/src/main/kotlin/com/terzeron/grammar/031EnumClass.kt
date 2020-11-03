package com.terzeron.grammar

import java.util.function.BinaryOperator
import java.util.function.IntBinaryOperator

enum class Direction {
    NORTH, SOUTH, WEST, EAST
}

enum class Color(val rgb: Int) {
    RED(0xFF0000),
    GREEN(0x00FF00),
    BLUE(0x0000FF),
}

fun enumTest1() {
    val dir = Direction.SOUTH
    println(dir)
    println(Direction.NORTH)

    val c = Color.RED
    println(c)
}

enum class ProtocolState {
    WAITING {
        override fun signal() = TALKING
    },
    TALKING {
        override fun signal() = WAITING
    };

    abstract fun signal(): ProtocolState
}

fun enumTest2() {
    val state1: ProtocolState = ProtocolState.WAITING
    println(state1)
    println(state1.signal())
    println(state1)
    val state2: ProtocolState = ProtocolState.TALKING
    println(state2)
    println(state2.signal())
    println(state2)
}

enum class IntArithmetics : BinaryOperator<Int>, IntBinaryOperator {
    PLUS {
        override fun apply(t: Int, u: Int): Int = t + u
    },
    TIMES {
        override fun apply(t: Int, u: Int): Int = t * u
    };

    override fun applyAsInt(t: Int, u: Int) = apply(t, u)
}

fun enumTest3() {
    val a = 13
    val b = 46
    // enum으로 정의된 값(여기서는 타입)에 대해 각각 apply 호출
    for (f in IntArithmetics.values()) {
        println("$f($a, $b) = ${f.apply(a, b)}")
    }
}

enum class RGB {
    RED, GREEN, BLUE
}

inline fun <reified T : Enum<T>> printAllValues() {
    print(enumValues<T>().joinToString { it.name })
}

fun enumTest4() {
    printAllValues<RGB>() // prints RED, GREEN, BLUE
}


fun main() {
    println("------ enumTest1 ------")
    enumTest1()
    println("------ enumTest2 ------")
    enumTest2()
    println("------ enumTest3 ------")
    enumTest3()
    println("------ enumTest4 ------")
    enumTest4()
}

