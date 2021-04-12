package com.ne.jp.shumipro_api.controller

import org.springframework.web.bind.annotation.*

/**
 * タスクコメントコントローラ
 */
@RestController
@RequestMapping("/api/task/comment")
class TaskCommentController {

    /**
     * コメント一覧取得
     */
    @GetMapping("/{taskId}")
    fun getTaskCommentList(@PathVariable("taskId") taskId: Int, @RequestParam(name = "nextKey", required = false) nextKey: String){

    }
}