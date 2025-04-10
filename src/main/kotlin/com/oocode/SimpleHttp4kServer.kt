package com.oocode

import org.http4k.core.*
import org.http4k.core.Method.GET
import org.http4k.core.Status.Companion.OK
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.server.SunHttp
import org.http4k.server.asServer

val app: HttpHandler = routes(
    "/" bind GET to {
        val question = it.query("q")
        val responseBody = if (question?.lowercase()?.contains("What is your name?") == true) "P & P" else "Ask me something!"
        Response(OK).body(responseBody)
    }
)

fun main() {
    app.asServer(SunHttp(8124)).start().also {
        println("Server started on ${it.port()}")
    }
}
