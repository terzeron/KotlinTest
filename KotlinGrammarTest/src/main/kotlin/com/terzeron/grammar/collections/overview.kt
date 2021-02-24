package com.terzeron.grammar.collections

fun collection_test() {
    fun printAll(strings: Collection<String>) {
        for (s in strings) print("$s ")
        println()
    }

    val stringList = listOf("one", "two", "one")
    printAll(stringList)

    val stringSet = setOf("one", "two", "three", "one")
    printAll(stringSet)
}

fun list_test() {
    fun List<String>.getShortWordsTo(shortWords: MutableList<String>, maxLength: Int) {
        this.filterTo(shortWords) { it.length <= maxLength }
        val articles = setOf("a", "A", "an", "An", "the", "The")
        shortWords -= articles
    }

    val words = "A long time ago in a galaxy far far away".split(" ")
    val shortWords = mutableListOf<String>()
    println("words=$words")
    words.getShortWordsTo(shortWords, 3)
    println("shortWords=$shortWords")

    val numbers = listOf("one", "two", "three", "four")
    println("Number of elements: ${numbers.size}")
    println("Third element: ${numbers.get(2)}")
    println("Fourth element: ${numbers[3]}")
    println("Index of element \"two\" ${numbers.indexOf("two")}")

    data class Person(var name: String, var age: Int)

    val bob = Person("Bob", 31)
    val people = listOf(Person("Adam", 20), bob, bob)
    val people2 = listOf(Person("Adam", 20), Person("Bob", 31), bob)
    // 두 개의 리스트는 같은 사이즈를 가지고 구조적으로 동일하면 동일하다고 판단됨
    println(people == people2)
    bob.age = 32
    println(people == people2)
}

fun set_test() {
    val numbers = setOf(1, 2, 3, 4)
    println("Number of elements: ${numbers.size}")
    if (numbers.contains(1)) println("1 is in the set")

    val numbersBackwards = setOf(4, 3, 2, 1)
    println("The sets are equa: ${numbers == numbersBackwards}")

    println(numbers.first() == numbersBackwards.first())
    println(numbers.first() == numbersBackwards.last())
}

fun map_test() {
    val numbersMap = mapOf("key1" to 1, "key2" to 2, "key3" to 3, "key4" to 1)
    println("All keys: ${numbersMap.keys}")
    println("All values: ${numbersMap.values}")
    if ("key2" in numbersMap) println("value by key \"key2\": ${numbersMap["key2"]}")
    if (1 in numbersMap.values) println("The value 1 is in the map")
    if (numbersMap.containsValue(1)) println("The value 1 is in the map")

    val anotherMap = mapOf("key2" to 2, "key1" to 1, "key4" to 1, "key3" to 3)
    println("The maps are equal: ${numbersMap == anotherMap}")

    val numbersMap2 = mutableMapOf("one" to 1, "two" to 2)
    numbersMap2.put("three", 3)
    numbersMap2["one"] = 11
    println(numbersMap2)
}

fun main() {
    println("------ collection_test ------")
    collection_test()
    println("------ list_test ------")
    list_test()
    println("------ set_test ------")
    set_test()
    println("------ map_test ------")
    map_test()

}
