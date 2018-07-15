package io.thomastodon.hexagonal.controller

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.thomastodon.hexagonal.core.domain.Objective
import io.thomastodon.hexagonal.core.usecase.AddObjectiveToStrategyUseCase
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.mock.web.MockHttpServletResponse
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.setup.MockMvcBuilders

class ObjectiveControllerTest {

    private val mockAddObjectiveToStrategyUseCase: AddObjectiveToStrategyUseCase = mock()
    private lateinit var objectiveController: ObjectiveController
    private lateinit var mockMvc: MockMvc

    @BeforeEach
    fun setUp() {
        objectiveController = ObjectiveController(mockAddObjectiveToStrategyUseCase, ObjectiveDtoToObjectiveTranslator())

        mockMvc = MockMvcBuilders.standaloneSetup(objectiveController).build()
    }

    @Nested
    inner class `when posting to the objective endpoint` {

        private lateinit var response: MockHttpServletResponse

        @BeforeEach
        fun setUp() {

            response = mockMvc.perform(post("/strategy/abc/objective")
                .content("{\"id\":\"def\"}")
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
            verify(mockAddObjectiveToStrategyUseCase).add("abc", Objective(id = "def"))
        }
    }
}
