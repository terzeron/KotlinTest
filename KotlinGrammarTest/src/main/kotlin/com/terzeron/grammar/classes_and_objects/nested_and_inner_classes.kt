package com.terzeron.grammar

import java.awt.event.MouseAdapter

class Outer1 {
    private val bar: Int = 1

    class Nested {
        fun foo() = 2
    }
}

class Outer2 {
    private val bar: Int = 1

    inner class Inner {
        fun foo() = bar
    }
}

fun nestedClassTest() {
    // Outer1.Nested가 nested class의 이름 - 괄호가 한 번 사용됨
    val demo1 = Outer1.Nested().foo()
    println(demo1)
    // Inner가 inner class의 이름 - 괄호가 두 번 사용됨
    val demo2 = Outer2().Inner().foo()
    println(demo2)
}

fun main() {
    println("------ nestedClassTest ------")
    nestedClassTest()
}