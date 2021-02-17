package com.terzeron.grammar.types

import java.io.File

fun is_operator_test() {
    val obj = "helloworld"
    if (obj is String) {
        println(obj.length)
    }

    if (obj !is String) {
        println("Not a string!")
    } else {
        println(obj.length)
    }
}

fun smart_cast_test() {
    fun demo(x: Any) {
        if (x is String) {
            println(x.length)
        }
    }

    fun demo2(x: Any) {
        if (x !is String) return
        println(x.length)
    }

    fun demo3(x: Any) {
        if (x !is String || x.length == 0) return
        if (x is String && x.length > 0) {
            println(x.length)
        }
    }

    fun demo4(x: Any) {
        when (x) {
            is Int -> println(x + 1)
            is String -> println(x.length + 1)
            is IntArray -> println(x.sum())
        }
    }

    demo(123)
    demo("hello")
    demo(listOf(1, 2, 3))
}

inline fun <reified A, reified B> Pair<*, *>.asPairOf(): Pair<A, B>? {
    if (first !is A || second !is B) return null
    return first as A to second as B
}

fun type_erasure_and_generic_type_checks_test() {
    val something = listOf(1, 2, 3)
    if (something is List<*>) {
        something.forEach { println(it) }
    }

    fun handleStrings(list: List<String>) {
        if (list is ArrayList) {
            for (item in list.toArray()) {
                println(item)
            }
        }
    }

    val l = ArrayList<String>()
    l.add("hello")
    l.add("world")
    l.add("music")
    handleStrings(l)

    val somePair: Pair<Any?, Any?> = "items" to listOf(1, 2, 3)
    println(somePair)
    val stringToSomething = somePair.asPairOf<String, Any>()
    println(stringToSomething)
    val stringToInt = somePair.asPairOf<String, Int>()
    println(stringToInt)
    val stringToList = somePair.asPairOf<String, List<*>>()
    println(stringToList)
    val stringToStringList = somePair.asPairOf<String, List<String>>()
    println(stringToStringList)
}

fun unchecked_cast_test() {
    fun readDictionary(file: File): Map<String, *> = file.inputStream().use {
        val dict = mutableMapOf<String, Int>()
        file.useLines { lines ->
            lines.map { line ->
                line.split(" ")
            }.forEach {
                dict.put(it.first(), it.last().toInt())
            }
        }
        dict
    }

    val intsFile = File("ints.dictionary")
    val intsDictionary: Map<String, Int> = readDictionary(intsFile) as Map<String, Int>
    intsDictionary.forEach { k, v -> println("$k -> $v") }
}

fun unsafe_cast_operator_test() {
    val y = 123
    val x: String? = y as String?
    println(x + "...")
}

fun safe_cast_operator_test() {
    val y = 123
    val x: String? = y as? String
    println(x + "...")
}

fun main() {
    println("--- is_operator_test ---")
    is_operator_test()
    println("--- smart_cast_test ---")
    smart_cast_test()
    println("--- type_erasure_and_generic_type_checks_test ---")
    type_erasure_and_generic_type_checks_test()
    println("--- unchecked_cast_test ---")
    unchecked_cast_test()
    println("--- safe_cast_operator_test ---")
    safe_cast_operator_test()
    println("--- unsafe_cast_operator_test ---")
    unsafe_cast_operator_test()
}

