package io.thomastodon.hexagonal.core.usecase

import io.thomastodon.hexagonal.core.domain.Objective

open class AddObjectiveToStrategyUseCase(
    private val strategyRepository: StrategyRepository,
    private val strategyPublisher: StrategyPublisher
) {

    open fun add(strategyId: String, objective: Objective) {

        strategyRepository.addObjectiveToStrategy(strategyId, objective)

        strategyRepository.findById(strategyId)
            .run { strategyPublisher.publish(this) }
    }
}