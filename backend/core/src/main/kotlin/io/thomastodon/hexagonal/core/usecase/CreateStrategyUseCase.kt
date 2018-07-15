package io.thomastodon.hexagonal.core.usecase

import io.thomastodon.hexagonal.core.domain.Strategy

open class CreateStrategyUseCase(private val saveStrategy: SaveStrategy) {


    open fun create(Strategy: Strategy) {
        saveStrategy.save(Strategy)
    }
}

