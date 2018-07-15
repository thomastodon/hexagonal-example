package io.thomastodon.hexagonal.controller

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.thomastodon.hexagonal.core.domain.Objective
import io.thomastodon.hexagonal.core.usecase.objective.CreateObjectiveUseCase
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

    private val mockCreateObjectiveUseCase: CreateObjectiveUseCase = mock()
    private lateinit var objectiveController: ObjectiveController
    private lateinit var mockMvc: MockMvc

    @BeforeEach
    fun setUp() {
        objectiveController = ObjectiveController(mockCreateObjectiveUseCase, ObjectiveDtoToObjectiveTranslator())

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
            verify(mockCreateObjectiveUseCase).create(Objective(id = "def"))
        }
    }
}
