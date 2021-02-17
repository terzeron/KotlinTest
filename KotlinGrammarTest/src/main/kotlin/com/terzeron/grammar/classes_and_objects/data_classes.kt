package com.terzeron.grammar.classes_and_objects

fun dataClassTest() {
    data class Person(val name: String) {
        var age: Int = 0
    }

    val person1 = Person("John")
    println(person1.age)
    person1.age = 10
    println(person1.age)

    val person2 = Person("John")
    println(person2.age)
    person2.age = 20
    println(person2.age)
}

fun copyingTest() {
    data class User(val name: String = "", val age: Int = 0)
    //fun copy(name: String = this.name, age: Int = this.age) = User(name, age)
    val jack = User(name = "Jack", age = 1)
    val olderJack = jack.copy(age = 2)

    val jane = User("Jane", 35)
    val (name, age) = jane
    println("$name, $age years of age")
}

fun main() {
    println("---- dataClassTest ----")
    dataClassTest()
    println("---- copyingTest ----")
    copyingTest()
}