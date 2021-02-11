package com.ne.jp.shumipro_api.controller

import com.ne.jp.shumipro_api.dto.TaskDto
import com.ne.jp.shumipro_api.response.TaskResponse
import com.ne.jp.shumipro_api.service.TaskService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/task")
class TaskController: BaseController() {
    @Autowired
    lateinit var taskService: TaskService

    @GetMapping("/{username}")
    fun getTaskList(@PathVariable("username") username: String): ResponseEntity<String> {
        val taskDtoList: List<TaskDto>? = taskService.getTaskList(username)
        if (taskDtoList is List<TaskDto>){
            // タスク取得成功
            val jsonString = gson.toJson(taskDtoList.map{it -> TaskResponse().setTaskResponse(it)}.toList())
            return createReponseEntity(HttpStatus.OK, jsonString)
        } else {
            // タスクが存在しない場合
            return createReponseEntity(HttpStatus.NOT_FOUND, "$username does not have task")
        }
    }
}