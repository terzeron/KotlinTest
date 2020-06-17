package com.terzeron.kotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinWebTest

fun main(args: Array<String>) {
    runApplication<KotlinWebTest>(*args)
}
