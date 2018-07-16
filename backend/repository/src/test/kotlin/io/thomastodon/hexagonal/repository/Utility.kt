package io.thomastodon.hexagonal.repository

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.datasource.SimpleDriverDataSource


internal object Utility {

    private val dataSource = SimpleDriverDataSource(
        com.mysql.cj.jdbc.Driver(),
        "jdbc:mysql://localhost:3306/haus?useSSL=false",
        "admin",
        "admin"
    )

    val jdbcTemplate = JdbcTemplate(dataSource)
}