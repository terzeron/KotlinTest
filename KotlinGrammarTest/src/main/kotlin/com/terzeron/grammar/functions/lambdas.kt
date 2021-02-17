package com.terzeron.grammar

fun collectionFoldTest() {
    // fold(initial:R, combine: ...) 함수는 R 타입의 초기값을 받아서 combine 해줌
    // combine(acc: R, nextElement: T) 함수는 R 타입으로 반환
    fun <T, R> Collection<T>.fold(
        initial: R,
        combine: (acc: R, nextElement: T) -> R
    ): R {
        var accumulator: R = initial
        for (element: T in this) {
            accumulator = combine(accumulator, element)
        }
        return accumulator
    }

    val items = listOf(1, 2, 3, 4, 5)
    items.fold(0,
        { acc: Int, i: Int
            ->
            print("acc = $acc, i = $i, ")
            val result = acc + i
            println("result = $result")
            result
        })
    // fold() 함수의 두번째 파라미터가 람다임
    // 람다 함수는 중괄호로 묶고 ->로 구분함. 첫번째는 인자 리스트, 두번째는 람다 함수 바디에 해당함
    // ex. { a, b -> a + b }

    val joinedToString = items.fold("Elements:", { acc, i -> acc + " " + i })
    println(joinedToString)
    val product = items.fold(1, Int::times)
    println(product)
}

fun transformerTest() {
    class IntTransformer : (Int) -> Int {
        fun add(i: Int): Int {
            return i + 1
        }

        override operator fun invoke(x: Int): Int = add(x)
    }

    val intFunction: (Int) -> Int = IntTransformer()
    println(intFunction(3))
    val a = { i: Int -> i + 1 }
    println(a(5))
}

fun repeatTest() {
    val repeatFun: String.(Int) -> String = { times -> this.repeat(times) }
    val twoParameters: (String, Int) -> String = repeatFun
    println(twoParameters("foo-", 5))

    fun runTransformation(f: (String, Int) -> String): String {
        return f("hello", 3)
    }

    val result = runTransformation(repeatFun)
    println(result)
}

fun invokeTest() {
    // 함수를 호출하는 두 가지 방법
    val stringPlus: (String, String) -> String = String::plus
    val intPlus: (Int, Int) -> Int = Int::plus
    println(stringPlus.invoke("<-", "->"))
    println(stringPlus("hello, ", "world"))

    println(intPlus.invoke(1, 1))
    println(intPlus(1, 2))
    ///println(2.intPlus(3)) // extension-like call
}

fun lambdaAndAnonymousFunctionTest() {
    val strings = listOf("john", "smith", "sam", "ripvalwinkle")
    fun maxString(
        dataList: List<String>,
        comparator: (result: String, next: String) -> Boolean
    ): String {
        var result: String = dataList[0]
        for (e: String in dataList) {
            if (comparator(result, e)) {
                result = e
            }
        }
        return result
    }
    println(maxString(strings, { a, b -> a.length < b.length }))

    fun compare(a: String, b: String): Boolean = a.length < b.length
    println(compare("john", "smith"))
}

fun lambdaTest() {
    val sum1: (Int, Int) -> Int = { x: Int, y: Int -> x + y }
    println(sum1(3, 46))
    val sum2 = { x: Int, y: Int -> x + y }
    println(sum2(51, 73))

    // trailing lambda
    val items = listOf(3, 6, 9)
    val product = items.fold(1) { acc, e -> acc * e }
    //var product = items.fold(1, { acc, e -> acc * e }
    println(product)

    // 첫번째 파라미터가 없다면 소괄호 생략 가능
    val ints = listOf(2, 3, 5, -10)
    println(ints.filter { it > 3 })

    // returning
    println(ints.filter {
        val shouldFilter = it > 0
        shouldFilter
    })
    println(ints.filter {
        val shouldFilter = it > 0
        return@filter shouldFilter
    })
    // chaining (LINQ-style)
    val strings = listOf("music", "java", "world", "hello")
    println(strings.filter { it.length == 5 }.sortedBy { it }.map { it.toUpperCase() })

    // unused parameter
    val intToStringMap = mapOf(1 to "x", 2 to "y", 3 to "z")
    println(intToStringMap.forEach { _, value -> println("$value!")})

    // anonymous function
    val add1 = fun(x: Int, y: Int): Int = x + y
    println(add1(3, 4))
    val add2 = fun(x: Int, y: Int): Int {
        return x + y
    }
    println(add2(5, 7))
    println(ints.filter(fun(item) = item > 2))
}

fun closureTest() {
    var sum = 0
    val ints = listOf(4, 2, -1, 5, 7, -10)
    // 람다는 클로저에 접근할 수 있음
    // 클로저는 바깥 스코프에서 선언된 변수를 포함하는 총체를 의미함
    // 이 예제에서는 sum이 클로저에 속한 것임
    ints.filter { it > 0 }.forEach { sum += it }
    println(sum)
}

fun receiverTest() {
    // plus가 receivert임
    // plus는 Int 타입의 메소드임
    // ex) 3.plus(4)
    val sum1: Int.(Int) -> Int = { other -> plus(other) }
    println(sum1(3, 4))
    // this를 사용할 수 있음
    val sum2 = fun Int.(other: Int): Int = this + other
    println(sum2(5, 6))

    // 람다 표현식은 receiver 타입을 추론 가능할 때 receiver와 함께 함수 리터럴로 사용될 수 있음
    // type-safe builder 사용방식
    class HTML {
        fun body(): String { return "<body><div>hello world</div></body>" }
    }

    fun html(init: HTML.() -> String): HTML {
        val html = HTML()
        html.init()
        return html
    }

    // lambda
    val response = html {
        // receiver object(HTML)에 대해서 메소드 호출
        body()
    }
    println(response)
    println(response.body())
}

fun main() {
    println("------ collectionFoldTest ------")
    collectionFoldTest()
    println("------ transformerTest ------")
    transformerTest()
    println("------ repeatTest ------")
    repeatTest()
    println("------ invokeTest ------")
    invokeTest()
    println("------ lambdaAndAnonymousFunctionTest ------")
    lambdaAndAnonymousFunctionTest()
    println("------ lambdaTest ------")
    lambdaTest()
    println("------ closureTest ------")
    closureTest()
    println("------ receiverTest ------")
    receiverTest()
}