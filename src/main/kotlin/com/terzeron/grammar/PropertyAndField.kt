package com.terzeron.grammar


class Address {
    var name: String = "Holmes, Sherlock"
    var street: String = "Baker"
    var city: String = "London"
    var state: String? = null
        private set
    var zip: String = "123456"
        set(value: String) {
            //zip = value // stack overflow
            field = value
        }
    var zipCode: Int = 0
        set(value: Int) {
            zip = value.toString()
        }
    val isNameEmpty
        get() = this.name.length == 0

    private var _table: MutableMap<String, Int>? = null
    public val table: MutableMap<String, Int>
        get() {
            if (_table == null) {
                _table = HashMap()
            }
            return _table ?: throw AssertionError("set to null by another thread")
        }
}

fun copyAddress(address: Address): Address {
    val result = Address()
    result.name = address.name
    result.street = address.street
    result.city = address.city
    //result.state = address.state
    result.zip = address.zip
    return result
}

fun instanceCopyTest() {
    val address = Address()
    val newAddress = copyAddress(address)
    println(newAddress.name)
}

fun backingFieldTest() {
    val address = Address()
    println(address.zip)
    address.zipCode = 9876
    println(address.zip)
}

fun backingPropertyTest() {
    val address = Address()
    var table = address.table
    println(table.size)
    table.put("key1", 10)
    table.put("key2", 20)
    println(table.size)
}

const val SUBSYSTEM_DEPRECATED: String = "This subsystem is deprecated"
@Deprecated(SUBSYSTEM_DEPRECATED)
fun constTest() {
    println("function to be deprecated")
}

fun main() {
    println("---- instanceCopyTest ----")
    instanceCopyTest()
    println("---- backingFieldTest ----")
    backingFieldTest()
    println("---- backingPropertyTest ----")
    backingPropertyTest()
    println("---- constTest ----")
    constTest()
}