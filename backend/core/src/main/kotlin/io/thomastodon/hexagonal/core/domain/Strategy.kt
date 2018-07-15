package io.thomastodon.hexagonal.core.domain

data class Strategy(
    private val id: String,
    private val objectives: List<Objective> = emptyList()
)