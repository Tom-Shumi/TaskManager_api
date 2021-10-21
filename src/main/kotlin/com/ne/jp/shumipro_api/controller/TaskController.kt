package com.ne.jp.shumipro_api.controller

import com.ne.jp.shumipro_api.dto.TaskDto
import com.ne.jp.shumipro_api.entity.ShumiproLoginUser
import com.ne.jp.shumipro_api.request.TaskRequest
import com.ne.jp.shumipro_api.request.TaskStatusRequest
import com.ne.jp.shumipro_api.response.TaskCommentResponse
import com.ne.jp.shumipro_api.response.TaskResponse
import com.ne.jp.shumipro_api.service.TaskCommentService
import com.ne.jp.shumipro_api.service.TaskService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
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
    @Autowired
    lateinit var taskCommentService: TaskCommentService

    /**
     * タスク一覧取得
     */
    @GetMapping("", "/", "/{status}")
    fun getTaskList(@AuthenticationPrincipal loginUser: ShumiproLoginUser, @PathVariable(name = "status", required = false) status: String? ): ResponseEntity<String> {
        val taskDtoList: List<TaskDto>? = taskService.getTaskList(loginUser.username, status)
        if (taskDtoList is List<TaskDto>){
            // タスク取得成功
            val jsonString = gson.toJson(taskDtoList.map{createTaskResponse(it)}.toList())
            return createResponseEntity(HttpStatus.OK, jsonString)
        } else {
            return createResponseEntity(HttpStatus.NO_CONTENT, null)
        }
    }

    fun createTaskResponse(taskDto: TaskDto): TaskResponse{
        val taskResponse = TaskResponse(taskDto)
        val taskCommentList = taskCommentService.getTaskCommentList(taskDto.id!!, null)
        taskResponse.comments = taskCommentList?.map{it -> TaskCommentResponse(it)}?.toList()
        return taskResponse
    }
    /**
     * タスク登録
     */
    @PostMapping
    fun registerTask(@Validated @RequestBody taskRequest: TaskRequest, errors: Errors, @AuthenticationPrincipal loginUser: ShumiproLoginUser): ResponseEntity<String> {
        val errorMsg: String? = checkErrors(errors)
        if (errorMsg is String){
            // リクエストが不正だった場合
            return createResponseEntity(HttpStatus.BAD_REQUEST, errorMsg)
        }
        val taskDtoRequest = TaskDto(taskRequest)
        taskDtoRequest.username = loginUser.username
        val taskDto: TaskDto? = taskService.registerTask(taskDtoRequest)
        return if (taskDto is TaskDto){
            // タスク登録成功
            val jsonString = gson.toJson(TaskResponse(taskDto))
            createResponseEntity(HttpStatus.OK, jsonString)
        } else {
            // ユーザが存在しない場合
            createResponseEntity(HttpStatus.BAD_REQUEST, "${loginUser.username} does not exist")
        }
    }

    /**
     * タスク更新
     */
    @PutMapping("/{taskId}")
    fun updateTask(@PathVariable("taskId") taskId: Int,@Validated @RequestBody taskRequest: TaskRequest, errors: Errors, @AuthenticationPrincipal loginUser: ShumiproLoginUser): ResponseEntity<String> {
        val errorMsg: String? = checkErrors(errors)
        if (errorMsg is String){
            // リクエストが不正だった場合
            return createResponseEntity(HttpStatus.BAD_REQUEST, errorMsg)
        }
        val taskDtoRequest = TaskDto(taskRequest)
        taskDtoRequest.id = taskId
        taskDtoRequest.username = loginUser.username
        val taskDto = taskService.updateTask(taskDtoRequest)
        return if (taskDto is TaskDto){
            // タスク更新成功
            val jsonString = gson.toJson(TaskResponse(taskDto))
            createResponseEntity(HttpStatus.OK, jsonString)
        } else {
            // タスクが存在しない場合
            createResponseEntity(HttpStatus.BAD_REQUEST, "this task does not exist")
        }
    }

    /**
     * タスクステータス更新
     */
    @PutMapping("/status/{taskId}")
    fun updateStatusTask(@PathVariable("taskId") taskId: Int, @Validated @RequestBody taskStatusRequest: TaskStatusRequest, errors: Errors, @AuthenticationPrincipal loginUser: ShumiproLoginUser): ResponseEntity<String> {
        val errorMsg: String? = checkErrors(errors)
        if (errorMsg is String){
            // リクエストが不正だった場合
            return createResponseEntity(HttpStatus.BAD_REQUEST, errorMsg)
        }
        val taskDto = taskService.updateStatusTask(taskId, taskStatusRequest.status)
        if (taskDto is TaskDto){
            // タスク更新成功
            val jsonString = gson.toJson(TaskResponse(taskDto))
            return createResponseEntity(HttpStatus.OK, jsonString)
        } else {
            // タスクが存在しない場合
            return createResponseEntity(HttpStatus.BAD_REQUEST, "this task does not exist")
        }
    }

    /**
     * タスク削除
     */
    @DeleteMapping("/{taskId}")
    fun deleteTask(@PathVariable("taskId") taskId: Int, @AuthenticationPrincipal loginUser: ShumiproLoginUser) : ResponseEntity<String> {
        if (taskService.deleteTask(taskId, loginUser.username) > 0){
            // タスク削除成功
            return createResponseEntity(HttpStatus.NO_CONTENT, "")
        } else {
            // タスクが存在しない場合
            return createResponseEntity(HttpStatus.NOT_FOUND, "this task does not found")
        }
    }
}