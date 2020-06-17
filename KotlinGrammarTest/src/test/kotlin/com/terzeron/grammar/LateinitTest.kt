package com.terzeron.grammar

import org.junit.Assert
import org.junit.Before
import org.junit.Test


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
        Assert.assertEquals(1, 1)
    }
}