package com.terzeron.grammar

import java.io.File

class Network {
    class Node {}
}
typealias NodeSet = Set<Network.Node>
typealias FileTable<K> = MutableMap<K, MutableList<File>>

class AAA {
    inner class Inner
}

class BBB {
    inner class Inner
}

typealias AInner = AAA.Inner
typealias BInnner = BBB.Inner

typealias Predicate<T> = (T) -> Boolean

fun typealiasTest() {
    fun foo(p: Predicate<Int>) = p(42)

    val f: (Int) -> Boolean = { it > 0 }
    println(foo(f))

    val p: Predicate<Int> = { it > 0 }
    println(listOf(1, -2).filter(p))
}

fun main() {
    println("---- typealiasTest ----")
    typealiasTest()
}