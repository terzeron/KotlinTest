package com.terzeron.grammar

import org.junit.Test
import org.junit.Before

class TestSubject {
    var text: String = "default text"
    fun print() {
        println("### $text ###");
    }
}

class LateinitTest {
    lateinit var subject: TestSubject

    @Before
    fun setup() {
        subject = TestSubject()
    }

    @Test
    fun test() {
        subject.print()
    }
}

