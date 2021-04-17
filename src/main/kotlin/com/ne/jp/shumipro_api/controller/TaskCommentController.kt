package com.ne.jp.shumipro_api.controller

import com.ne.jp.shumipro_api.dto.TaskCommentDto
import com.ne.jp.shumipro_api.dto.TaskDto
import com.ne.jp.shumipro_api.entity.ShumiproLoginUser
import com.ne.jp.shumipro_api.request.TaskCommentRequest
import com.ne.jp.shumipro_api.response.TaskCommentResponse
import com.ne.jp.shumipro_api.response.TaskResponse
import com.ne.jp.shumipro_api.service.TaskCommentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.validation.Errors
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

/**
 * タスクコメントコントローラ
 */
@RestController
@RequestMapping("/api/task/comment")
class TaskCommentController: BaseController() {

    @Autowired
    lateinit var taskCommentService: TaskCommentService

    /**
     * コメント一覧取得
     */
    @GetMapping("/{taskId}")
    fun getTaskCommentList(@PathVariable("taskId") taskId: Int, @RequestParam(name = "nextKey", required = false) nextKey: Int?): ResponseEntity<String> {
        val taskCommentDtoList = taskCommentService.getTaskCommentList(taskId, nextKey)
        return if (taskCommentDtoList is List<TaskCommentDto>){
            // タスクコメント取得成功
            val jsonString = gson.toJson(taskCommentDtoList.map{it -> TaskCommentResponse().setTaskCommentResponse(it)}.toList())
            createReponseEntity(HttpStatus.OK, jsonString)
        } else {
            createReponseEntity(HttpStatus.NO_CONTENT, null)
        }
    }

    /**
     * コメント登録処理
     */
    @PostMapping("/{taskId}")
    fun registerTaskComment(@PathVariable("taskId") taskId: Int, @Validated @RequestBody taskCommentRequest: TaskCommentRequest
                            , errors: Errors, @AuthenticationPrincipal loginUser: ShumiproLoginUser): ResponseEntity<String>{
        val errorMsg: String? = checkErrors(errors)
        if (errorMsg is String){
            // リクエストが不正だった場合
            return createReponseEntity(HttpStatus.BAD_REQUEST, errorMsg)
        }
        val taskCommentDtoRequest = TaskCommentDto(taskId = taskId, username = loginUser.username, comment = taskCommentRequest.comment)
        val taskCommentDto = taskCommentService.registerTaskComment(taskCommentDtoRequest)
        if (taskCommentDto is TaskCommentDto){
            // タスクコメント登録成功
            val jsonString = gson.toJson(TaskCommentResponse().setTaskCommentResponse(taskCommentDto))
            return createReponseEntity(HttpStatus.OK, jsonString)
        } else {
            // タスクが存在しない場合
            return createReponseEntity(HttpStatus.BAD_REQUEST, "This task does not exist")
        }
    }

    /**
     * コメント更新処理
     */
    @PutMapping("/{taskId}/{taskCommentId}")
    fun updateTaskComment(@PathVariable("taskId") taskId: Int, @PathVariable("taskCommentId") taskCommentId: Int
                          , @Validated @RequestBody taskCommentRequest: TaskCommentRequest, errors: Errors
                          , @AuthenticationPrincipal loginUser: ShumiproLoginUser): ResponseEntity<String>{
        val errorMsg: String? = checkErrors(errors)
        if (errorMsg is String){
            // リクエストが不正だった場合
            return createReponseEntity(HttpStatus.BAD_REQUEST, errorMsg)
        }
        val taskCommentDtoRequest = TaskCommentDto(taskId = taskId, username = loginUser.username, comment = taskCommentRequest.comment)
        val taskCommentDto = taskCommentService.updateTaskComment(taskCommentDtoRequest)
        return if (taskCommentDto is TaskCommentDto){
            // タスクコメント更新成功
            val jsonString = gson.toJson(TaskCommentResponse().setTaskCommentResponse(taskCommentDto))
            createReponseEntity(HttpStatus.OK, jsonString)
        } else {
            // タスクコメントが存在しない場合
            createReponseEntity(HttpStatus.BAD_REQUEST, "this task comment does not exist")
        }
    }
}