package io.thomastodon.hexagonal.core.usecase

interface GetStrategyById {
    fun getById(id: String)
}