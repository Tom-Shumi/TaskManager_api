package com.ne.jp.shumipro_api.response

import com.ne.jp.shumipro_api.dto.TaskCommentDto
import com.ne.jp.shumipro_api.util.DateUtil
import java.text.SimpleDateFormat
import java.util.*

data class TaskCommentResponse(
    var id: Int?,
    var taskId: Int,
    var username: String,
    var comment: String?,
    var createDate: String
) {

    constructor(dto: TaskCommentDto):
            this(dto.id, dto.taskId, dto.username, dto.comment, DateUtil.toStringYYYYMMDD(dto.createDate))
}