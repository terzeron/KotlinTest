package com.terzeron.grammar.classes_and_objects

inline class Name(val s: String) {
    val length: Int
        get() = s.length

    fun greet() {
        println("Hello, $s")
    }
}

interface Printable {
    fun prettyPrint(): String
}

inline class Name2(val s: String) : Printable {
    override fun prettyPrint(): String = "Let's $s!"
}

interface I
/*
inline class Foo(val i: Int) : I

fun asInline(f: Foo) {}
fun <T> asGeneric(x: T) {}
fun asInterface(i: I) {}
fun asNullable(i: Foo?) {}
fun <T> id(x: T): T = x
*/
fun main() {
    val name = Name("Kotlin")
    name.greet()
    println(name.length)

    val name2 = Name2("Kotlin")
    println(name2.prettyPrint())

    /*
    asInline(f)
    asGeneric(f)
    asInterface(f)
    asNullable(f)

    val c = id(f)
    println(c)
    */
}
