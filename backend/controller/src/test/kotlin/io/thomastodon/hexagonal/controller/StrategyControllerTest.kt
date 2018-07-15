package io.thomastodon.hexagonal.controller

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.thomastodon.hexagonal.core.domain.Strategy
import io.thomastodon.hexagonal.core.usecase.CreateStrategyUseCase
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders

class StrategyControllerTest {

    private val mockCreateStrategyUseCase: CreateStrategyUseCase = mock()
    private lateinit var StrategyController: StrategyController
    private lateinit var mockMvc: MockMvc

    @BeforeEach
    fun setUp() {
        StrategyController = StrategyController(mockCreateStrategyUseCase, StrategyDtoToStrategyTranslator())

        mockMvc = MockMvcBuilders.standaloneSetup(StrategyController).build()
    }

    @Test
    fun `posting to strategy, returns a 201`() {

        mockMvc.perform(post("/strategy")
            .content("{\"id\":\"abc\"}")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated)
    }

    @Test
    fun `posting to strategy, delegates to the use case`() {

        mockMvc.perform(post("/strategy")
            .content("{\"id\":\"abc\"}")
            .contentType(MediaType.APPLICATION_JSON))

        verify(mockCreateStrategyUseCase).create(Strategy(id = "abc"))
    }
}