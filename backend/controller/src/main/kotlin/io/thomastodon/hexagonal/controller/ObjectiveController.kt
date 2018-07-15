package io.thomastodon.hexagonal.controller

import io.thomastodon.hexagonal.core.usecase.objective.CreateObjectiveUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
class ObjectiveController(
    private val createObjectiveUseCase: CreateObjectiveUseCase,
    private val objectiveDtoToObjectiveTranslator: ObjectiveDtoToObjectiveTranslator
) {

    @PostMapping("/strategy/{id}/objective")
    fun post(@RequestBody objectiveDto: ObjectiveDto): ResponseEntity<Unit> {

        objectiveDto
            .run { objectiveDtoToObjectiveTranslator.translate(this) }
            .run { createObjectiveUseCase.create(this) }

        return ResponseEntity.created(URI("/dagobah")).build()
    }

}
