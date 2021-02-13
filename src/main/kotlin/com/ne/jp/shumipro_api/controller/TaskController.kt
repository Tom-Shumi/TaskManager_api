package com.ne.jp.shumipro_api.controller

import com.ne.jp.shumipro_api.dto.TaskDto
import com.ne.jp.shumipro_api.request.TaskRequest
import com.ne.jp.shumipro_api.response.TaskResponse
import com.ne.jp.shumipro_api.service.TaskService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.Errors
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

/**
 * タスクコントローラ
 */
@RestController
@RequestMapping("/api/task")
class TaskController: BaseController() {
    @Autowired
    lateinit var taskService: TaskService

    /**
     * タスク一覧取得
     */
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

    /**
     * タスク登録
     */
    @PostMapping
    fun registerTask(@Validated @RequestBody taskRequest: TaskRequest, errors: Errors): ResponseEntity<String> {
        val errorMsg: String? = checkErrors(errors)
        if (errorMsg is String){
            // リクエストが不正だった場合
            return createReponseEntity(HttpStatus.BAD_REQUEST, errorMsg)
        }
        val taskDtoRequest = TaskDto().setTaskDto(taskRequest)
        val taskDto: TaskDto? = taskService.registerTask(taskDtoRequest)
        if (taskDto is TaskDto){
            // タスク登録成功
            val jsonString = gson.toJson(TaskResponse().setTaskResponse(taskDto))
            return createReponseEntity(HttpStatus.OK, jsonString)
        } else {
            // ユーザが存在しない場合
            return createReponseEntity(HttpStatus.BAD_REQUEST, "${taskRequest.username} does not exist")
        }
    }

    /**
     * タスク更新
     */
    @PutMapping("/{taskId}")
    fun updateTask(@PathVariable("taskId") taskId: Int,@Validated @RequestBody taskRequest: TaskRequest, errors: Errors): ResponseEntity<String> {
        val errorMsg: String? = checkErrors(errors)
        if (errorMsg is String){
            // リクエストが不正だった場合
            return createReponseEntity(HttpStatus.BAD_REQUEST, errorMsg)
        }
        val taskDtoRequest = TaskDto().setTaskDto(taskRequest)
        taskDtoRequest.id = taskId
        val taskDto = taskService.updateTask(taskDtoRequest)
        if (taskDto is TaskDto){
            // タスク更新成功
            val jsonString = gson.toJson(TaskResponse().setTaskResponse(taskDto))
            return createReponseEntity(HttpStatus.OK, jsonString)
        } else {
            // タスクが存在しない場合
            return createReponseEntity(HttpStatus.BAD_REQUEST, "this task does not exist")
        }
    }
}