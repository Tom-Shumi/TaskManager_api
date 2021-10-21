package com.ne.jp.shumipro_api.response

import com.fasterxml.jackson.annotation.JsonFormat
import com.ne.jp.shumipro_api.dto.TaskDto
import com.ne.jp.shumipro_api.util.DateUtil
import java.text.SimpleDateFormat
import java.util.*

data class TaskResponse(
    var id: Int?,
    var username: String?,
    var task: String,
    var priority: Int,
    var status: Int,
    var description: String?,
    var planDate: String?,
    var doneDate: String?,
    var comments: List<TaskCommentResponse>?
) {

    constructor(dto: TaskDto): this(dto.id, dto.username, dto.task, dto.priority, dto.status, dto.description,
        DateUtil.toStringYYYYMMDD(dto.planDate), DateUtil.toStringYYYYMMDD(dto.doneDate), null)

}