package com.terzeron.grammar.classes_and_objects

open class Outer {
    private var a = 1
    protected open var b = 2
    internal var c = 3
    var d = 4

    protected class Nested {
        public var e: Int = 5
    }
}

class Subclass : Outer() {
    fun test() {
        //a = 3 // invisible
        b = 5
        c = 5
        d = 6
        val f = Nested()
        f.e = 7
    }
}

class Unrelated(o: Outer) {
    fun test() {
        val o = Outer()
        //o.a = 3 // invisible
        //o.b = 4 // invisible
        o.c = 5
        o.d = 6
        //val f = Nested() // invisible
    }
}

fun visibilityTest() {
    val s = Subclass()
    s.test()

    val u = Unrelated(s)
    u.test()
}

fun main() {
    println("---- visibilityTest() ----")
    visibilityTest()
}