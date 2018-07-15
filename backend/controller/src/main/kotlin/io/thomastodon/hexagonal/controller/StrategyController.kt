package io.thomastodon.hexagonal.controller

import io.thomastodon.hexagonal.core.domain.Strategy
import io.thomastodon.hexagonal.core.usecase.CreateStrategyUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
open class StrategyController(
    private val createStrategyUseCase: CreateStrategyUseCase,
    private val StrategyDtoToStrategyTranslator: Translator<StrategyDto, Strategy>
) {

    @PostMapping("/strategy")
    open fun createStrategy(@RequestBody StrategyDto: StrategyDto): ResponseEntity<Unit> {

        StrategyDto
            .run { StrategyDtoToStrategyTranslator.translate(this) }
            .run { createStrategyUseCase.create(this) }

        return ResponseEntity.created(URI("/hello")).build()
    }
}