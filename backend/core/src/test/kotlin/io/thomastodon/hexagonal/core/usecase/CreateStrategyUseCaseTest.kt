package io.thomastodon.hexagonal.core.usecase

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.thomastodon.hexagonal.core.domain.Strategy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class CreateStrategyUseCaseTest {

    private val mockStrategyRepository: StrategyRepository = mock()
    private val mockStrategyPublisher: StrategyPublisher = mock()
    private lateinit var createStrategyUseCase: CreateStrategyUseCase

    @BeforeEach
    fun setUp() {
        createStrategyUseCase = CreateStrategyUseCase(mockStrategyRepository, mockStrategyPublisher)
    }

    @Nested
    inner class `when creating a strategy` {

        private val strategy = Strategy(id = "abc")

        @BeforeEach
        internal fun setUp() {
            createStrategyUseCase.create(strategy)
        }

        @Test
        fun `it saves the strategy`() {

            verify(mockStrategyRepository).save(strategy)
        }

        @Test
        fun `it publishes the strategy`() {

            verify(mockStrategyPublisher).publish(strategy)
        }
    }
}
