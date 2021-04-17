package com.ne.jp.shumipro_api.dto

import com.ne.jp.shumipro_api.entity.Task
import com.ne.jp.shumipro_api.entity.TaskComment
import java.util.*

data class TaskCommentDto(
    var id: Int? = 0
    , var taskId: Int? = 0
    , var username: String? = ""
    , var comment: String? = ""
    , var createDate: Date? = null
) {

    fun setTaskCommentDto(taskComment: TaskComment): TaskCommentDto{
        this.id = taskComment.id
        this.taskId = taskComment.taskId
        this.username = taskComment.username
        this.comment = taskComment.comment
        this.createDate = taskComment.create_date
        return this
    }
}