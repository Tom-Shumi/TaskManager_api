package com.ne.jp.shumipro_api.dto

import com.ne.jp.shumipro_api.entity.TaskComment
import java.time.LocalDate
import java.util.*

data class TaskCommentDto(
    var id: Int?
    , var taskId: Int
    , var username: String
    , var comment: String?
    , var createDate: LocalDate
) {

    constructor(entity: TaskComment):
            this(entity.id, entity.taskId, entity.username, entity.comment, entity.createDate)

    constructor(id: Int?, taskId: Int, username: String, comment: String?) :
            this(id, taskId, username, comment, LocalDate.now())
}