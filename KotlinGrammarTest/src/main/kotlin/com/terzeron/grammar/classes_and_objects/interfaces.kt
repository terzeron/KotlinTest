package com.terzeron.grammar.classes_and_objects

interface MyInterface {
    fun bar()
    fun foo() {
        println(prop)
    }

    val prop: Int
    val propertyWithImplementation: String
        get() = "music" + 123
}

class Child : MyInterface {
    override val prop: Int = 29
    override fun bar() {
        println("Child.bar()")
    }
}

fun overrideTest() {
    val child = Child()
    child.bar()
    child.foo()
    println(child.prop)
    println(child.propertyWithImplementation)
}

interface Named {
    val name: String
}

interface Person3 : Named {
    val firstName: String
    val lastName: String

    override val name: String get() = "$firstName $lastName"
}

class Position(val name: String) {
}

data class Employee(
    override val firstName: String,
    override val lastName: String,
    val position: Position
) : Person3

fun inheritanceTest() {
    val employee = Employee("Mike", "Parker", Position("CEO"))
    println(employee)
}

interface A {
    fun foo() {
        println("A.foo()")
    }

    fun bar()
}

interface B {
    fun foo() {
        println("B.foo()")
    }

    fun bar() {
        println("B.bar()")
    }
}

class C : A {
    override fun bar() {
        println("C.bar()")
    }
}

class D : A, B {
    override fun foo() {
        super<A>.foo()
        super<B>.foo()
    }

    override fun bar() {
        super<B>.bar()
    }
}

fun resolvingTest() {
    val c = C()
    c.foo()
    c.bar()

    val d = D()
    d.foo()
    d.bar()
}

fun main() {
    println("---- overrideTest ----")
    overrideTest()
    println("---- inheritanceTest ----")
    inheritanceTest()
    println("---- resolvingTest ----")
    resolvingTest()
}