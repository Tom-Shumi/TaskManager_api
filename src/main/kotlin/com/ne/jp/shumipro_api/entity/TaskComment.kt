package com.ne.jp.shumipro_api.entity

import com.ne.jp.shumipro_api.dto.TaskCommentDto
import java.util.*

data class TaskComment(
    var id: Int? = 0
    , var task_id: Int? = 0
    , var username: String? = ""
    , var comment: String? = ""
    , var create_date: Date? = null
) {

    fun setTaskComment(taskCommentDto: TaskCommentDto): TaskComment {
        this.id = taskCommentDto.id
        this.task_id = taskCommentDto.taskId
        this.username = taskCommentDto.username
        this.comment = taskCommentDto.comment
        this.create_date = taskCommentDto.createDate
        return this
    }
}