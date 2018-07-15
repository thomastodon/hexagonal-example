package io.thomastodon.hexagonal.controller

import io.thomastodon.hexagonal.core.domain.Strategy

class StrategyDtoToStrategyTranslator : Translator<StrategyDto, Strategy> {

    override fun translate(source: StrategyDto) = Strategy(id = source.id!!)
}