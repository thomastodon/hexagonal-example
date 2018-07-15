package io.thomastodon.hexagonal.core.usecase

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.thomastodon.hexagonal.core.domain.Objective
import io.thomastodon.hexagonal.core.domain.Strategy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyString

class AddObjectiveToStrategyUseCaseTest {

    private val mockStrategyRepository: StrategyRepository = mock()
    private val mockStrategyPublisher: StrategyPublisher = mock()
    private lateinit var addObjectiveToStrategyUseCase: AddObjectiveToStrategyUseCase

    @BeforeEach
    fun setUp() {
        addObjectiveToStrategyUseCase = AddObjectiveToStrategyUseCase(
            mockStrategyRepository,
            mockStrategyPublisher
        )
    }

    @Nested
    inner class `when adding an objective to a strategy that exists` {

        private val objective = Objective(id = "def")
        private val strategy = Strategy(id = "abc", objectives = listOf(objective))

        @BeforeEach
        internal fun setUp() {

            whenever(mockStrategyRepository.findById(anyString())).thenReturn(strategy)

            addObjectiveToStrategyUseCase.add("abc", objective)
        }

        @Test
        fun `it saves the objective`() {

            verify(mockStrategyRepository).addObjectiveToStrategy("abc", objective)
        }

        @Test
        fun `it gets the associated strategy from the repository and publishes it`() {

            verify(mockStrategyRepository).findById("abc")
            verify(mockStrategyPublisher).publish(strategy)
        }
    }
}