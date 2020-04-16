package com.terzeron.kotlin

import io.ktor.application.call
import io.ktor.features.BadRequestException
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.*
import io.ktor.util.KtorExperimentalAPI

@KtorExperimentalAPI
fun Routing.todo(service: TodoService) {
    route("todos") {
        get {
            call.respond(service.getAll())
        }
        get("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
                ?: throw BadRequestException("Parameter id is null")
            call.respond(service.getById(id))
        }
        post {
            val body = call.receive<TodoRequest>()
            service.new(body.content)
            call.response.status(HttpStatusCode.Created)
        }
        put("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
                ?: throw BadRequestException("Parameter id is null")
            val body = call.receive<TodoRequest>()
            service.renew(id, body)
            call.response.status(HttpStatusCode.NoContent)
        }
        delete("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
                ?: throw BadRequestException("Parameter id is null")
            service.delete(id)
            call.response.status(HttpStatusCode.NoContent)
        }
    }
}