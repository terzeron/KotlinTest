package com.terzeron.grammar

interface Vehicle {
    fun print()
    val message: String
    fun printMessage()
}

class VehicleImpl(val x: Int) : Vehicle {
    override fun print() {
        println(message)
    }
    override val message = "BaseImpl: x = $x"
    override fun printMessage() { println(message) }
}

// Car는 Vehicle 클래스를 상속
// by 구문에 의해 v가 Car 객체 내부에 저장되고 모든 메소드를 Vehicle에서 가져와서 생성함
class Car(v: Vehicle) : Vehicle by v {
    override val message = "Message of Car"
    override fun printMessage() { println("br-br-br...") }
}

fun main() {
    val v = VehicleImpl(10)
    val car = Car(v)
    car.print() // Vehicle의 print()를 호출하여 message 값을 출력함
    car.printMessage() // Car 자신의 printMessage()를 호출함
    println(car.message) // Car 자신의 message를 출력함
}