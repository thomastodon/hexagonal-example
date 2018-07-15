package io.thomastodon.hexagonal.core.usecase

import io.thomastodon.hexagonal.core.domain.Strategy

open class CreateStrategyUseCase(
    private val strategyRepository: StrategyRepository,
    private val strategyPublisher: StrategyPublisher
) {

    open fun create(strategy: Strategy) {

        strategyRepository.save(strategy)
        strategyPublisher.publish(strategy)
    }
}
