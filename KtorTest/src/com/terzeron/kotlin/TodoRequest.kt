package com.terzeron.kotlin

import java.time.LocalDateTime

data class TodoRequest(val content: String, val done: Boolean?, val createdAt: LocalDateTime?, val updatedAt: LocalDateTime?)
