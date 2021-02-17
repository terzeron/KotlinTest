package com.terzeron.grammar


fun expressionReturnTest() {
    class Person {
        var name: String
        constructor(name: String) {
            this.name = name
        }
    }
    val person = Person("john")
    val s = person.name ?: return
}

fun breakTest() {
    loop13@ for (i in 1..10) {
        for (j in 1..10) {
            //println("i=$i, j=$j")
            if (i ==2 && j == 2) break@loop13
        }
    }
}

fun returnAtLabelTest1() {
    listOf(1, 2, 3, 4, 5).forEach {
        if (it == 3) return
        println(it)
    }
    println("this point is unreachable")
}

fun returnAtLabelTest2() {
    listOf(1, 2, 3, 4, 5).forEach lit@{
        if (it == 3) return@lit // local return
        println(it)
    }
    println("done with explicit label")
}

fun returnAtLabelTest3() {
    listOf(1, 2, 3, 4, 5).forEach {
        if (it == 3) return@forEach // local return
        println(it)
    }
    println("done with implicit label")
}

fun returnAtLabelTest4() {
    run loop@{
        listOf(1, 2, 3, 4, 5).forEach {
            if (it == 3) return@loop
            println(it)
        }
        println("done with nested loop")
    }
}

fun returnAtLabelTest5() {
    run loop@{
        listOf(1, 2, 3, 4, 5).forEach {
            if (it == 3) return@loop 1
            println(it)
        }
        println("done with nested loop with return value")
    }
}
fun main() {
    println("---- expressionReturnTest ----")
    expressionReturnTest()
    println("---- breakTest ----")
    breakTest()
    println("---- returnAtLabelTest1 ----")
    returnAtLabelTest1()
    println("---- returnAtLabelTest2 ----")
    returnAtLabelTest2()
    println("---- returnAtLabelTest3 ----")
    returnAtLabelTest3()
    println("---- returnAtLabelTest4 ----")
    returnAtLabelTest4()
    println("---- returnAtLabelTest5 ----")
    returnAtLabelTest5()
}