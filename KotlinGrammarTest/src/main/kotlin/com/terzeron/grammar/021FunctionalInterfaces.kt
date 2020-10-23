package com.terzeron.grammar

// functional interface or SAM(single abstract method) interface
// 추상 멤버를 단 하나만 가짐. 다른 구상 멤버들은 가질 수 있음
fun interface KRunnable {
    fun invoke()
}

fun interface IntPredicate {
    fun accept(i: Int): Boolean
}

fun functionalInterface1() {
    // 일반적인 interface
    val isEven1 = object : IntPredicate {
        override fun accept(i: Int): Boolean {
            return i % 2 == 0
        }
    }

    // SAM
    val isEven2 = IntPredicate { it % 2 == 0 }

    println(isEven1.accept(3))
    println(isEven1.accept(4))
    println(isEven2.accept(5))
    println(isEven2.accept(6))
}

fun main() {
    println("---- functionalInterface1() ----")
    functionalInterface1()
}