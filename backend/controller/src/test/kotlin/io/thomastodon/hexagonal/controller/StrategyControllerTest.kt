package io.thomastodon.hexagonal.controller

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.thomastodon.hexagonal.core.domain.Strategy
import io.thomastodon.hexagonal.core.usecase.CreateStrategyUseCase
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.mock.web.MockHttpServletResponse
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
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

    @Nested
    inner class `when posting to the strategy endpoint` {

        private lateinit var response: MockHttpServletResponse

        @BeforeEach
        internal fun setUp() {

            response = mockMvc.perform(post("/strategy")
                .content("{\"id\":\"abc\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .response
        }

        @Test
        fun `it returns a response with a created status`() {
                assertThat(response.status).isEqualTo(201)
        }

        @Test
        fun `it delegates to the use case`() {
            verify(mockCreateStrategyUseCase).create(Strategy(id = "abc"))
        }
    }
}