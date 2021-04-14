package com.ne.jp.shumipro_api.controller

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
    fun getTaskCommentList(@PathVariable("taskId") taskId: Int, @RequestParam(name = "nextKey", required = false) nextKey: String?): ResponseEntity<String> {
        val taskCommentDtoList = taskCommentService.getTaskCommentList(taskId, nextKey)
        return createReponseEntity(HttpStatus.NO_CONTENT, null);
    }
}