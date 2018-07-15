package io.thomastodon.hexagonal.core.usecase

import io.thomastodon.hexagonal.core.domain.Strategy

interface SaveStrategy {

    fun save(Strategy: Strategy)

}