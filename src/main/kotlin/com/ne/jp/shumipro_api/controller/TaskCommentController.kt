package com.ne.jp.shumipro_api.controller

import com.ne.jp.shumipro_api.dto.TaskCommentDto
import com.ne.jp.shumipro_api.dto.TaskDto
import com.ne.jp.shumipro_api.response.TaskCommentResponse
import com.ne.jp.shumipro_api.response.TaskResponse
import com.ne.jp.shumipro_api.service.TaskCommentService
import com.ne.jp.shumipro_api.service.TaskService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
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
        if (taskCommentDtoList is List<TaskCommentDto>){
            // タスクコメント取得成功
            val jsonString = gson.toJson(taskCommentDtoList.map{it -> TaskCommentResponse().setTaskCommentResponse(it)}.toList())
            return createReponseEntity(HttpStatus.OK, jsonString)
        } else {
            return createReponseEntity(HttpStatus.NO_CONTENT, null)
        }
        return createReponseEntity(HttpStatus.NO_CONTENT, null);
    }
}