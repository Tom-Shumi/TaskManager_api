package com.ne.jp.shumipro_api.controller

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ExtendWith(SpringExtension::class)
@WebMvcTest(TaskController::class)
@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest
class TaskControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun `when the user exists, the user info is returned`() {
        val inputJson = ""
        val expectedJson = ""

        mockMvc.perform(
            MockMvcRequestBuilders.get("/api/task")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andExpect(content().json(expectedJson))
            .andReturn()
    }
}