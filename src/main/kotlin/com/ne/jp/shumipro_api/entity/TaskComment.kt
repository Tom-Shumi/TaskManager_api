package com.ne.jp.shumipro_api.entity

import com.ne.jp.shumipro_api.dto.TaskCommentDto
import java.util.*

data class TaskComment(
    var id: Int? = 0
    , var taskId: Int? = 0
    , var username: String? = ""
    , var comment: String? = ""
    , var createDate: Date? = null
) {

    fun setTaskComment(taskCommentDto: TaskCommentDto): TaskComment {
        this.id = taskCommentDto.id
        this.taskId = taskCommentDto.taskId
        this.username = taskCommentDto.username
        this.comment = taskCommentDto.comment
        this.createDate = taskCommentDto.createDate
        return this
    }
}