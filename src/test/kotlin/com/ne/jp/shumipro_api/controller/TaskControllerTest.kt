package com.ne.jp.shumipro_api.controller

import com.github.springtestdbunit.DbUnitTestExecutionListener
import com.github.springtestdbunit.annotation.DatabaseOperation
import com.github.springtestdbunit.annotation.DatabaseSetup
import com.github.springtestdbunit.annotation.DbUnitConfiguration
import com.google.gson.Gson
import com.ne.jp.shumipro_api.ShumiproApiApplication
import com.ne.jp.shumipro_api.entity.ShumiproLoginUser
import com.ne.jp.shumipro_api.entity.User
import com.ne.jp.shumipro_api.mapper.TaskMapper
import com.ne.jp.shumipro_api.request.TaskRequest
import com.ne.jp.shumipro_api.response.TaskResponse
import com.ne.jp.shumipro_api.util.CsvDataSetLoader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user
import org.springframework.test.context.TestExecutionListeners
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener
import org.springframework.test.context.transaction.TransactionalTestExecutionListener
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.RequestBuilder

@ExtendWith(SpringExtension::class)
@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader::class)
@DatabaseSetup(value = ["/dbUnit_data/controller/TaskControllerTest/"], type = DatabaseOperation.CLEAN_INSERT)
@TestExecutionListeners(
    DependencyInjectionTestExecutionListener::class,
    TransactionalTestExecutionListener::class,
    DbUnitTestExecutionListener::class,
)
@AutoConfigureMockMvc()
@SpringBootTest(classes = [ShumiproApiApplication::class])
class TaskControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc
    @Autowired
    private lateinit var taskMapper: TaskMapper

    open val gson = Gson()

    @Test
    fun getTask_test1() {
        val loginUser = createLoginUser()
        val expected = listOf(TaskResponse(1,"shumiya","test",1, 1))
        val expectedJson = gson.toJson(expected)

        val builder: RequestBuilder = MockMvcRequestBuilders.get("/api/task")
            .with(user(loginUser))
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)

        mockMvc.perform(builder)
            .andExpect(status().isOk)
            .andExpect(content().json(expectedJson))
    }

    @Test
    fun registerTask_task1(){
        val input = TaskRequest("register_test",1, 1)
        val inputJson = gson.toJson(input)

        val loginUser = createLoginUser()
        val builder: RequestBuilder = MockMvcRequestBuilders.post("/api/task")
            .with(user(loginUser))
            .with(csrf())
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content(inputJson)

        mockMvc.perform(builder)
            .andExpect(status().isOk)

        val taskList = taskMapper.getTaskByUsername("shumiya")
        val targetTask = taskList?.get(0)
        assertThat(targetTask?.username).isEqualTo("shumiya")
        assertThat(targetTask?.task).isEqualTo("register_test")
        assertThat(targetTask?.priority).isEqualTo(1)
        assertThat(targetTask?.status).isEqualTo(1)

    }

    fun createLoginUser(): ShumiproLoginUser = ShumiproLoginUser(User("shumiya", "shumiya", 1, 1))
}