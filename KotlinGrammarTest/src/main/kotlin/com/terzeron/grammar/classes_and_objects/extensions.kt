package com.terzeron.grammar.classes_and_objects
fun swap_test() {
    // 클래스 확장
    // MutableList에 대해 .swap()이라는 표현을 통해 메소드를 확장하고 있음
    fun MutableList<Int>.swap1(index1: Int, index2: Int) {
        val tmp = this[index1]
        this[index1] = this[index2]
        this[index2] = tmp
    }

    // generic 사용 가능
    fun <T> MutableList<T>.swap2(index1: Int, index2: Int) {
        val tmp = this[index1]
        this[index1] = this[index2]
        this[index2] = tmp
    }

    val list1 = mutableListOf(1, 2, 3, 4)
    list1.swap1(0, 2)
    println(list1)

    val list2 = mutableListOf("hello", "world", 3, "java")
    list2.swap2(0, 2)
    println(list2)
}


fun extension_function_test() {
    open class Shape

    class Rectangle: Shape()

    // Shape1과 Rectangle1 클래스에 대해 .getName() 확장
    fun Shape.getName() = "Shape1"
    fun Rectangle.getName() = "Rectangle1"

    fun printClassName(s: Shape) {
        // 파라미터 s가 Shape 타입으로 선언되면서 "Shape"이 출력됨
        // 정적으로 결정됨
        println(s.getName())
    }

    printClassName(Rectangle())
}

fun class_method_and_extension_function_test() {
    class Example {
        fun printFunctionType() {
            println("class method")
        }
    }

    fun Example.printFunctionType() {
        println("extension function")
    }

    fun Example.printFunctionType(i: Int) {
        println("extension function $i")
    }

    // 같은 이름으로 중복 존재하면 클래스 메소드가 우선함
    Example().printFunctionType()
    // signature가 다르면 확장 함수가 우선할 수도 있음
    Example().printFunctionType(1)
}

fun nullable_receiver_test() {
    // nullable receiver, ?.method()
    fun Any?.toString(): String {
        if (this == null) return "null"
        return toString()
    }

    val x: Int? = null
    println(x.toString())
    val y: Int = 3
    println(y.toString())
}

val <T> kotlin.collections.List<T>.lastIndex: Int
    get() = size - 3

fun property_test() {
    val list = listOf("a", "b", "c", "d", "e", "f")
    println(list.lastIndex)
    // lastIndex는 이미 list에 기본적으로 제공되고 있는 property인데
    // 확장 속성을 재정의해서 사용할 수 있음
}

class MyClass1 {
    companion object { }
}

fun companion_extension_test() {
    fun MyClass1.Companion.printCompanion() {
        println("companion")
    }

    MyClass1.printCompanion()
}

fun receiver_test() {
    class Host(val hostname: String) {
        fun printHostname() {
            print(hostname)
        }
    }

    class Connection(val host: Host, val port: Int) {
        fun printPort() {
            print(port)
        }
        // Host 클래스를 Connection 클래스 내부에서 확장하고 있음
        fun Host.printConnectionString() {
            printHostname()
            print(":")
            printPort()
            print("\n")
        }
        fun connect() {
            host.printConnectionString()
        }
    }
    Connection(Host("kotl.in"), 443).connect()
}

fun receiver_in_hierarchy_test() {
    open class Base {}
    class Derived: Base() {
    }
    open class BaseCaller {
        open fun Base.printFunctionInfo() {
            println("Base extension function in BaseCaller")
        }
        open fun Derived.printFunctionInfo() {
            println("Derived extension function in BaseCaller")
        }

        fun call(b: Base) {
            b.printFunctionInfo()   // call the extension function
        }
    }

    class DerivedCaller: BaseCaller() {
        override fun Base.printFunctionInfo() {
            println("Base extension function in DerivedCaller")
        }

        override fun Derived.printFunctionInfo() {
            println("Derived extension function in DerivedCaller")
        }
    }

    BaseCaller().call(Base())
    DerivedCaller().call(Base())
    DerivedCaller().call(Derived())
}


fun main() {
    println("------ swap_test() ------")
    swap_test()
    println("------ extension_function_test() ------")
    extension_function_test()
    println("------ class_method_and_extension_function_test() ------")
    class_method_and_extension_function_test()
    println("------ nullable_receiver_test() ------")
    nullable_receiver_test()
    println("------ property_test() ------")
    property_test()
    println("------ companion_extension_test() ------")
    companion_extension_test()
    println("------ receiver_test() ------")
    receiver_test()
    println("------ receiver_in_hierarchy_test() ------")
    receiver_in_hierarchy_test()
}
