package com.ne.jp.shumipro_api.controller

import com.google.gson.Gson
import com.ne.jp.shumipro_api.dto.TaskDto
import com.ne.jp.shumipro_api.service.TaskCommentService
import com.ne.jp.shumipro_api.service.TaskService
import com.ne.jp.shumipro_api.util.HandlerMethodArgumentResolverImpl
import com.ne.jp.shumipro_api.util.MockitoUtil
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.mockito.InjectMocks
import org.mockito.Mock
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.mockito.Mockito.`when`


@ExtendWith(SpringExtension::class)
class TaskControllerTest {

    private lateinit var mockMvc: MockMvc

    @Mock
    private lateinit var taskService: TaskService

    @Mock
    private lateinit var taskCommentService: TaskCommentService

    @InjectMocks
    private lateinit var taskController: TaskController

    val handlerMethodArgumentResolver = HandlerMethodArgumentResolverImpl()

    @BeforeEach
    fun initMock() {
        mockMvc = MockMvcBuilders.standaloneSetup(taskController).setCustomArgumentResolvers(handlerMethodArgumentResolver).build()
    }

    private val gson = Gson()

    @Test
    fun getTaskListOne() {
        val taskDto = TaskDto(id = 1)
        `when`(taskService.getTaskList(MockitoUtil.any(), MockitoUtil.any())).thenReturn(listOf(taskDto))

        mockMvc.perform(get("/api/task/"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$[0].id").value(1))
    }
}