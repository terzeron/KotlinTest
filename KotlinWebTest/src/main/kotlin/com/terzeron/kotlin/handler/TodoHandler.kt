package com.terzeron.kotlin.handler

import com.terzeron.kotlin.domain.Todo
import com.terzeron.kotlin.domain.TodoRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters.fromObject
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.*
import reactor.core.publisher.Mono
import java.net.URI
import java.time.LocalDateTime
import java.util.*
import java.util.stream.Collectors.toList

@Component
class TodoHandler {
    private val repo: TodoRepository

    constructor(repo: TodoRepository) {
        this.repo = repo
    }

    //    fun getAll(req: ServerRequest): Mono<ServerResponse> = ok()
//            .contentType(MediaType.APPLICATION_JSON)
//            .bodyWithType(Mono.just(repo.findAll()))
//            .switchIfEmpty(notFound().build())
    fun getAll(req: ServerRequest): Mono<ServerResponse> =
            repo.findAll().filter(Objects::nonNull)
                    .collect(toList())
                    .flatMap { ok().body(fromObject(it)) }

    //    fun getById(req: ServerRequest): Mono<ServerResponse> = ok()
//            .contentType(MediaType.APPLICATION_JSON)
//            .bodyWithType(Mono.justOrEmpty(repo.findById(req.pathVariable("id").toLong())))
//            .switchIfEmpty(notFound().build())
    fun getById(req: ServerRequest): Mono<ServerResponse> =
            repo.findById(req.pathVariable("id").toLong())
                    .flatMap { ok().body(fromObject(it)) }
                    .switchIfEmpty(status(HttpStatus.NOT_FOUND).build())

    //   fun save(req: ServerRequest): Mono<ServerResponse> = ok()
//            .contentType(MediaType.APPLICATION_JSON)
//            .body(req.bodyToMono(Todo::class.java)
//                    .switchIfEmpty(Mono.empty())
//                    .filter(Objects::nonNull)
//                    .flatMap { todo ->
//                        Mono.fromCallable {
//                            repo.save(todo)
//                        }.then(Mono.just(todo))
//                    }
//            )
//            .switchIfEmpty(notFound().build())
    fun save(req: ServerRequest): Mono<ServerResponse> =
            repo.saveAll(req.bodyToMono(Todo::class.java))
                    .flatMap { created(URI.create("/todos/${it.id}")).build() }
                    .next()

    //    fun done(req: ServerRequest): Mono<ServerResponse> = ok()
//            .contentType(MediaType.APPLICATION_JSON)
//            .body(Mono.justOrEmpty(repo.findById(req.pathVariable("id").toLong()))
//                    .switchIfEmpty(Mono.empty())
//                    .filter(Objects::nonNull)
//                    .flatMap { todo ->
//                        Mono.fromCallable {
//                            todo.done = true
//                            todo.modifiedAt = LocalDateTime.now()
//                            repo.save(todo)
//                        }.then(Mono.just(todo))
//                    }
//            )
//            .switchIfEmpty(notFound().build())
    fun done(req: ServerRequest): Mono<ServerResponse> =
            repo.findById(req.pathVariable("id").toLong())
                    .filter(Objects::nonNull)
                    .flatMap { todo ->
                        todo.done = true
                        todo.modifiedAt = LocalDateTime.now()
                        repo.save(todo)
                    }
                    .flatMap {
                        it?.let { ok().build() }
                    }
                    .switchIfEmpty(status(HttpStatus.NOT_FOUND).build())

    //    fun delete(req: ServerRequest): Mono<ServerResponse> = ok()
//            .contentType(MediaType.APPLICATION_JSON)
//            .body(Mono.justOrEmpty(repo.findById(req.pathVariable("id").toLong()))
//                    .switchIfEmpty(Mono.empty())
//                    .filter(Objects::nonNull)
//                    .flatMap { todo ->
//                        Mono.fromCallable {
//                            repo.delete(todo)
//                        }.then(Mono.just(todo))
//                    }
//            )
//            .switchIfEmpty(notFound().build())
    fun delete(req: ServerRequest): Mono<ServerResponse> =
            repo.findById(req.pathVariable("id").toLong())
                    .filter(Objects::nonNull)
                    .flatMap { todo ->
                        ok().build(repo.deleteById(todo.id))
                    }
                    .switchIfEmpty(status(HttpStatus.NOT_FOUND).build())
}