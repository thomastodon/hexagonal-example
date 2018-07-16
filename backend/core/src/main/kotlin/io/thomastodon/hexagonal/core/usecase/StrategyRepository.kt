package io.thomastodon.hexagonal.core.usecase

import io.thomastodon.hexagonal.core.domain.Objective
import io.thomastodon.hexagonal.core.domain.Strategy

interface StrategyRepository {

    fun save(strategy: Strategy)

    fun findById(id: String): Strategy

    fun addObjectiveToStrategy(strategyId: String, objective: Objective)

}