package io.thomastodon.hexagonal.core.usecase.strategy

import io.thomastodon.hexagonal.core.domain.Strategy

interface StrategyRepository {

    fun save(Strategy: Strategy)

}