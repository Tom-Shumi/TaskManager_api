package com.ne.jp.shumipro_api.entity

import com.ne.jp.shumipro_api.dto.TaskCommentDto
import java.time.LocalDate
import java.util.*

data class TaskComment(
    var id: Int?,
    var taskId: Int,
    var username: String,
    var comment: String?,
    var createDate: LocalDate
) {

    constructor(dto: TaskCommentDto):
            this(dto.id, dto.taskId, dto.username, dto.comment, dto.createDate)
}