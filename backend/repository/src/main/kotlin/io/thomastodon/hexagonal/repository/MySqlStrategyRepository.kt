package io.thomastodon.hexagonal.repository

import io.thomastodon.hexagonal.core.domain.Objective
import io.thomastodon.hexagonal.core.domain.Strategy
import io.thomastodon.hexagonal.core.usecase.StrategyRepository
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper


class MySqlStrategyRepository(
    private val jdbcTemplate: JdbcTemplate
) : StrategyRepository {

    private val strategyRowMapper: RowMapper<Strategy> = StrategyRowMapper()

    override fun save(strategy: Strategy) {

        val sql = "INSERT INTO strategy (id) VALUES (${strategy.id})"

        jdbcTemplate.update(sql)
    }

    override fun findById(id: String): Strategy {

        val sql = "SELECT * FROM strategy WHERE id=$id"

        return jdbcTemplate.queryForObject(sql, strategyRowMapper)
    }

    override fun addObjectiveToStrategy(strategyId: String, objective: Objective) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}