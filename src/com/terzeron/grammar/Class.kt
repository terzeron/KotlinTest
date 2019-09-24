package com.terzeron.grammar

class Invoice {}

class Empty

class Person constructor(firstName: String) {}

class Woman(firstName: String) {}

class InitOrderDemo(name: String) {
    val firstProperty = "First property: $name".also(::println)

    init {
        println("First initializer block that prints ${name}")
    }

    val secondProperty = "Second property: ${name.length}".also(::println)

    init {
        println("Second initializer block that prints ${name.length}")
    }
}

class Customer(name: String) {
    val customerKey = name.toUpperCase()
}

//class Customer1 public @Inject constructor(name: String) { }

class Man(val firstName: String, val lastName: String, var age: Int) {}

class Person1 {
    var children: MutableList<Person1> = mutableListOf<Person1>()

    constructor(parent: Person1?) {
        parent?.children?.add(this)
    }
}

class Person2(val name: String) {
    var children: MutableList<Person2> = mutableListOf<Person2>()

    constructor(name: String, parent: Person2) : this(name) {
        parent.children.add(this)
    }
}

class Animal {
    init {
        println("init block")
    }

    constructor(i: Int) {
        println("constructor i=$i")
    }
}

// private constructor의 용도
// https://stackoverflow.com/questions/2062560/what-is-the-use-of-making-constructor-private-in-a-class
// 1) 싱글턴 디자인 패턴
// 2) 클래스 인스턴스 생성의 갯수를 제한하기 위해
// 3) 정적 팩토리 메소드를 이용하여 객체 생성에 대한 의미있는 이름을 부여하기 위해
// 4) 정적 유틸리티 클래스 또는 상수 클래스
// 5) 서브클래싱을 방지하기 위해
// 6) 빌더 디자인 패턴과 immutable 클래스를 생성하기 위해
class Bird private constructor(val name: String) {
    companion object {
        operator fun invoke(name: String): Bird {
            println("private constructor with companion object, name=$name")
            return Bird(name)
        }
    }
}

// 'open' means 'overridable'
open class Base(p: Int) {}

class Derived(p: Int) : Base(p) {}

class Context {}
class AttributeSet {}
open class View {
    constructor(ctx: Context) {
        println("constructor of View class")
    }

    constructor(ctx: Context, attrs: AttributeSet) {
        println("constructor of View class")
    }
}

class MyView : View {
    constructor(ctx: Context) : super(ctx)
    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs)
}

open class Shape {
    open val count: Int = 0
    open fun draw() {
        println("draw() of Shape")
    }
}

class Circle() : Shape() {
    override val count = 4
    final override fun draw() {
        println("draw() of Circle")
    }
}

open class Base1(val name: String) {
    init {
        println("init of Base class")
    }

    open val size: Int = name.length.also { println("initializing size in Base: $it") }
}

class Derived1(
    name: String,
    val lastName: String
) : Base1(name.capitalize().also { println("argument for Base: $it") }) {
    init {
        println("init of Derived1 class")
    }

    override val size: Int = (super.size + lastName.length).also { println("Initializing size in Derived: $it") }
}

open class Rectangle {
    open fun draw() {
        println("drawing a rectangle with border color $borderColor")
    }

    val borderColor: String get() = "black"
}

class FilledRectangle : Rectangle() {
    override fun draw() {
        super.draw()
        println("filling the rectangle with fill color $fillColor")
    }
    val fillColor: String get() = super.borderColor
}

interface Polygon {
    fun draw() {
        println("drawing a polygon")
    }
}

class Square() : Rectangle(), Polygon {
    override fun draw() {
        super<Rectangle>.draw();
        super<Polygon>.draw();
    }
}

fun main() {
    val demo = InitOrderDemo("jack")
    println("demo=$demo")

    val customer = Customer("jill")
    println("customer=${customer.customerKey}")

    val man = Man("Thomas", "Moore", 72)
    println("man=${man.firstName} ${man.lastName} ${man.age}")
    //val customer1 = Customer1("Jackson")

    println("----------------------------")
    val parent = Person1(null)
    val person1 = Person1(parent)
    val person2 = Person2("buzz")
    println("parent=$parent")
    println("person1=$person1")
    println("person2=$person2")
    println("person2.name=${person2.name}")

    println("----------------------------")
    val animal = Animal(3)
    val bird = Bird("hawk")

    println("----------------------------")
    val rectangle = Shape()
    val circle = Circle()
    rectangle.draw()
    println("${rectangle.count}")
    circle.draw()
    println("${circle.count}")

    println("----------------------------")
    val base1 = Base1("helloworld")
    val derived1 = Derived1("Michael", "Jordan")

    println("----------------------------")
    val rect1 = Rectangle()
    val rect2 = FilledRectangle()
    rect1.draw()
    rect2.draw()

    println("----------------------------")
    val sqr = Square()
    sqr.draw()
}

