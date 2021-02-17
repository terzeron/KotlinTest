package com.terzeron.grammar.classes_and_objects

// 한정된 타입
// 어떤 의미에서 enum 클래스의 확장이라고 볼 수 있음
sealed class Expr
data class Const(val number: Double) : Expr()
data class Sum(var e1: Expr, val e2: Expr) : Expr()
object NotANumber : Expr()

fun sealedClassTest() {
    fun eval(expr: Expr): Double = when (expr) {
        is Const -> expr.number
        is Sum -> eval(expr.e1) + eval(expr.e2)
        NotANumber -> Double.NaN
    }

    val c: Const = Const(3.0)
    println(eval(c));
}

fun main() {
    println("---- sealedClassTest ----")
    sealedClassTest()
}