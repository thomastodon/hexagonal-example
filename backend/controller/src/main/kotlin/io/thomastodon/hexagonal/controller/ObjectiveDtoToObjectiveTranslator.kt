package io.thomastodon.hexagonal.controller

import io.thomastodon.hexagonal.core.domain.Objective

class ObjectiveDtoToObjectiveTranslator : Translator<ObjectiveDto, Objective> {

    override fun translate(source: ObjectiveDto) = Objective(
        id = source.id!!
    )
}