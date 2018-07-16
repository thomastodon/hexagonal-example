package io.thomastodon.hexagonal.repository

import io.thomastodon.hexagonal.core.domain.Strategy
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

class StrategyRowMapper : RowMapper<Strategy> {

    override fun mapRow(rs: ResultSet, rowNum: Int) = Strategy(
        id = rs.getString("id")
    )

}