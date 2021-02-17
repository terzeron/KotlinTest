package com.terzeron.grammar

open class A1(x: Int) {
    var x: Int = 3
    public open val y: Int = x
}

interface B1 {}

fun objectTest() {
    // object expression: 상속하진 않지만 간단히 확장해서 사용
    val ab: A1 = object : A1(1), B1 {
        // open class로 선언된 A1의 y 값을 override함
        override val y = 25
    }

    println(ab.x)
    println(ab.y)
}

fun foo() {
    // adhoc object expression
    val adHoc = object {
        var x: Int = 0
        var y: Int = 0
    }
    print(adHoc.x + adHoc.y)
}

class C1 {
    // return type: object
    private fun foo() = object {
        val x: String = "xxx"
    }

    // return type: Any
    fun publicFoo() = object {
        val x: String = "yyy"
    }

    fun bar() {
        val x1 = foo().x
        //val x2 = publicFoo().x // error
        println("x1 in bar: $x1")
    }
}

fun functionOverrideTest() {
    val c = C1()
    println(c)
    println(c.publicFoo())
    c.bar()
}

class DataProvider {
    var name: String = ""

    constructor(name: String) {
        this.name = name
    }

    override fun toString(): String {
        return "DP:" + name
    }
}

// 객체 선언, 싱글턴
object DataProviderManager {
    var list: MutableList<DataProvider> = mutableListOf()
    fun register(provider: DataProvider) {
        list.add(provider)
    }

    val allDataProviders: Collection<DataProvider>
        get() = list
}

fun objectDeclarationTest() {
    DataProviderManager.register(DataProvider("station"))
    DataProviderManager.register(DataProvider("busstop"))
    DataProviderManager.register(DataProvider("airport"))
    println(DataProviderManager.allDataProviders)
}

class MyClass {
    // 클래스 내부에서 선언하여 싱글턴처럼 사용
    companion object Factory {
        fun create(): MyClass = MyClass()
    }
}

class MyClass2 {
    // 클래스 내부에서 선언하여 싱글턴처럼 사용
    companion object {
        fun create(): MyClass2 = MyClass2()
    }
}

fun companionObjectTest() {
    val instance = MyClass.create()
    println(instance)
}

class M1 {
    companion object Named {}
}

class M2 {
    companion object {}
}

fun companionObjectWithoutMethodTest() {
    val x = M1
    val y = M2
    println(x)
    println(y)
}

interface Factory<T> {
    fun create(): T
}

class MyClass3 {
    companion object : Factory<MyClass3> {
        override fun create(): MyClass3 = MyClass3()
    }
}

fun companionObjectWithInterfaceTest() {
    val f: Factory<MyClass3> = MyClass3
    println(f)
}

fun main() {
    println("------ objectTest ------")
    objectTest()
    println("------ functionOverrideTest ------")
    functionOverrideTest()
    println("------ objectDeclarationTest ------")
    objectDeclarationTest()
    println("------ companionObjectTest ------")
    companionObjectTest()
    println("------ companionObjectWithoutMethodTest ------")
    companionObjectWithoutMethodTest()
    println("------ companionObjectWithInterfaceTest ------")
    companionObjectWithInterfaceTest()
}