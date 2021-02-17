package com.terzeron.grammar.classes_and_objects

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

