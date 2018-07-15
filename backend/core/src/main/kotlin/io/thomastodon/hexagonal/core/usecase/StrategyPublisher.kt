package io.thomastodon.hexagonal.core.usecase

import io.thomastodon.hexagonal.core.domain.Strategy

interface StrategyPublisher {

    fun publish(strategy: Strategy)
}