package com.terzeron.grammar.classes_and_objects

import com.google.gson.Gson

fun classWithGenericTest() {
    class Box<T>(t: T) {
        var value = t
    }

    val box1: Box<Int> = Box<Int>(1)
    val box2 = Box(1)
    println(box1.value)
}


interface Source<out T> {
    fun nextT(): T
}

/*
interface Comparable<in T> {
    operator fun compareTo(other: T): Int
}
 */

fun interfaceWithGenericTest() {
    fun demo1(strs: Source<String>) {
        val objects: Source<Any> = strs
        println(objects.nextT())
    }

    fun demo2(x: Comparable<Number>) {
        println(x.compareTo(1.0))
        val y: Comparable<Double> = x
        println(y)
    }

    class StringSource : Source<String> {
        override fun nextT(): String {
            return "hello"
        }
    }

    class DoubleComparable : Comparable<Number> {
        override fun compareTo(other: Number): Int {
            return -1
        }

    }

    val strs = StringSource()
    demo1(strs)
    val number = DoubleComparable()
    demo2(number)
}

fun typeProjectionTest() {
    fun copy(from: Array<Any>, to: Array<Any>) {
        assert(from.size == to.size)
        for (i in from.indices)
            to[i] = from[i]
    }

    val ints: Array<Any> = arrayOf(1, 2, 3)
    val any = Array<Any>(3) { "" }
    println(Gson().toJson(any))
    copy(ints, any)
    println(Gson().toJson(any))

    fun fill(dest: Array<in String>, value: String) {}
}

fun genericFunctionTest() {
    fun <T> singletonList(item: T): List<T> {
        var resultList = listOf(item)
        return resultList
    }

    fun <T> T.basicToString(): String {
        return "world"
    }

    val list1 = singletonList<Int>(3)
    println(list1)
    val list2 = singletonList(10)
    println(list2)

    val str = "hello"
    println(str.basicToString())
}

fun upperBoundTest() {
    fun <T : Comparable<T>> sort(list: List<T>) {

    }

    sort(listOf(10.7, 43.2, 49.1))
    //sort(listOf(HashMap<Int, String>())) // error, not Comparable

    fun <T> copyWhenGreater(list: List<T>, threshold: T): List<String>
            where T : CharSequence, T : Comparable<T> {
        return list.filter { it > threshold }.map { it.toString() }
    }

    var list = listOf("ABC", "java world", "foo bar")
    println(copyWhenGreater(list, "icecream"))
}

fun main() {
    println("---- classWithGenericTest ----")
    classWithGenericTest()
    println("---- interfaceWithGenericTest ----")
    interfaceWithGenericTest()
    println("---- typeProjectionTest ----")
    typeProjectionTest()
    println("---- genericFunctionTest ----")
    genericFunctionTest()
    println("---- upperBoundTest ----")
    upperBoundTest()
}