package io.thomastodon.hexagonal.controller

import io.thomastodon.hexagonal.core.usecase.AddObjectiveToStrategyUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
class ObjectiveController(
    private val addObjectiveToStrategyUseCase: AddObjectiveToStrategyUseCase,
    private val objectiveDtoToObjectiveTranslator: ObjectiveDtoToObjectiveTranslator
) {

    @PostMapping("/strategy/{strategyId}/objective")
    fun post(
        @PathVariable strategyId: String,
        @RequestBody objectiveDto: ObjectiveDto
    ): ResponseEntity<Unit> {

        objectiveDto
            .run { objectiveDtoToObjectiveTranslator.translate(this) }
            .run { addObjectiveToStrategyUseCase.add(strategyId, this) }

        return ResponseEntity.created(URI("/dagobah")).build()
    }
}
