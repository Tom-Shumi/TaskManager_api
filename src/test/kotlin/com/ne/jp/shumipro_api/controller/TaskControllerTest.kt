package com.ne.jp.shumipro_api.controller

import com.google.gson.Gson
import com.ne.jp.shumipro_api.ShumiproApiApplication
import com.ne.jp.shumipro_api.dto.TaskDto
import com.ne.jp.shumipro_api.entity.ShumiproLoginUser
import com.ne.jp.shumipro_api.entity.User
import com.ne.jp.shumipro_api.response.TaskResponse
import com.ne.jp.shumipro_api.service.TaskService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.RequestBuilder

@ExtendWith(SpringExtension::class)
@AutoConfigureMockMvc()
@SpringBootTest(classes = [ShumiproApiApplication::class])
class TaskControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc
    @MockkBean
    lateinit var mockTaskService: TaskService

    open val gson = Gson()

    @Test
    fun getTask_task1() {
        val user:User = User("shumiya", "shumiya", 1, 1)
        val loginUser = ShumiproLoginUser(user)
        val expectedTaskDtoList = listOf(TaskDto(1,"shumiya", "test", 1, 1))
        val expected = listOf(TaskResponse(1,"shumiya","test",1, 1))
        val expectedJson = gson.toJson(expected)
        every { mockTaskService.getTaskList("shumiya") } returns expectedTaskDtoList

        val builder: RequestBuilder = MockMvcRequestBuilders.get("/api/task")
            .with(user(loginUser))
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)

        mockMvc.perform(builder)
            .andExpect(status().isOk)
            .andExpect(content().json(expectedJson))
    }
}