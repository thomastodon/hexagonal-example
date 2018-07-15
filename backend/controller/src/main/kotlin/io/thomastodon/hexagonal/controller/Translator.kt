package io.thomastodon.hexagonal.controller

interface Translator<T, U> {

    fun translate(source: T): U
}