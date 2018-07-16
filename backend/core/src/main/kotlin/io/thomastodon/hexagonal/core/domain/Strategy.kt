package io.thomastodon.hexagonal.core.domain

data class Strategy(
    val id: String,
    val objectives: List<Objective> = emptyList()
)