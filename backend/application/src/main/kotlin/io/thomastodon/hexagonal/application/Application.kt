package io.thomastodon.hexagonal.application

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder

@SpringBootApplication
open class Application

fun main(args: Array<String>) {
    SpringApplicationBuilder(Application::class.java).run(*args)
}
