package io.thomastodon.hexagonal.repository

import io.thomastodon.hexagonal.core.domain.Strategy
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class MySqlStrategyRepositoryTest {

    private lateinit var strategyRepository: MySqlStrategyRepository

    @BeforeEach
    fun setUp() {
        strategyRepository = MySqlStrategyRepository(Utility.jdbcTemplate)
    }

    @Test
    fun `save, saves a record`() {

        strategyRepository.save(Strategy(id = "abc"))

        val strategy = strategyRepository.findById("abc")

        assertThat(strategy.id).isEqualTo("abc")
    }
}