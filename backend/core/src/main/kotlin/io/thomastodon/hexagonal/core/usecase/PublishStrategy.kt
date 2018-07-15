package io.thomastodon.hexagonal.core.usecase

import io.thomastodon.hexagonal.core.domain.Strategy

interface PublishStrategy {

    fun publish(Strategy: Strategy)
}