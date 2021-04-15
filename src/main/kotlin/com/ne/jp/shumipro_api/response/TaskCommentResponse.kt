package com.ne.jp.shumipro_api.response

import com.ne.jp.shumipro_api.dto.TaskCommentDto
import com.ne.jp.shumipro_api.util.DateUtil
import java.text.SimpleDateFormat
import java.util.*

data class TaskCommentResponse(
    var id: Int? = 0
    , var taskId: Int? = 0
    , var username: String? = ""
    , var comment: String? = ""
    , var createDate: String? = ""
) {

    fun setTaskCommentResponse(taskCommentDto: TaskCommentDto): TaskCommentResponse {
        this.id = taskCommentDto.id
        this.taskId = taskCommentDto.taskId
        this.username = taskCommentDto.username
        this.comment = taskCommentDto.comment
        this.createDate = DateUtil.toStringYYYYMMDDHHMMSS(taskCommentDto.createDate)
        return this
    }
}