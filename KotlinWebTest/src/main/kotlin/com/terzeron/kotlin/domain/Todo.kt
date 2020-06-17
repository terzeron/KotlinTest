package com.terzeron.kotlin.domain

import java.time.LocalDateTime
import javax.persistence.Id
import javax.persistence.Table

@Table(name = "todos")
class Todo {
    @Id
    var id: Long = 0
    var content: String? = null
    var done: Boolean = false
    var createdAt: LocalDateTime = LocalDateTime.now()
    var modifiedAt: LocalDateTime = createdAt
}