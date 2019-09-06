package com.terzeron.kotlin.domain

import org.springframework.data.repository.reactive.ReactiveCrudRepository

//interface TodoRepository : JpaRepository<Todo, Long>
interface TodoRepository : ReactiveCrudRepository<Todo, Long>