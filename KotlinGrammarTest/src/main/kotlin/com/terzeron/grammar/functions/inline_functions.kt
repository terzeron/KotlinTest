package com.terzeron.grammar.functions

inline class Password(val value: String)

fun inline_class_test() {
    val password1: String = "Your password"
    val password2 = Password("Your password")
    println(password1)
    println(password2)
}

data class UserInfoResponse1(
    val index: Long,
    val indexId: Long,
    val userId: String,
    val userName: String,
    val age: Int,
    val registrationDate: Long
)

inline class Index(val index: Long)
inline class IndexId(val index: Long)
data class UserInfoResponse2(
    val index: Index, // primitive long type으로 변환됨
    val indexId: IndexId // primitive long type으로 변환됨
)

fun inline_function_test() {
    "inline test".also { println(it) }
}

// 익명 클래스를 생성하게 됨
fun String?.notNull1(body: String.() -> Unit) {
    this?.body()
}

// 익명 클래스 생성없이 inline 처리됨
inline fun String?.notNull2(body: String.() -> Unit) {
    this?.body()
}

fun inline_function_test2() {
    "aaa".notNull1 {
        println("this message $this")
    }

    "aaa".notNull2 {
        println("this message $this")
    }
}

fun main(){
    println("------ inline_class_test ------")
    inline_class_test()
    println("------ inline_function_test ------")
    inline_function_test()
    println("------ inline_function_test2 ------")
    inline_function_test2()

}