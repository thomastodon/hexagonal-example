package io.thomastodon.hexagonal.core.usecase.strategy

import io.thomastodon.hexagonal.core.domain.Strategy

interface StrategyPublisher {

    fun publish(strategy: Strategy)
}