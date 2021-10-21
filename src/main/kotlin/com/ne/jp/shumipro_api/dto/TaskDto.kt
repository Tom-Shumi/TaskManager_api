package com.ne.jp.shumipro_api.dto

import com.ne.jp.shumipro_api.entity.Task
import com.ne.jp.shumipro_api.request.TaskRequest
import com.ne.jp.shumipro_api.request.UserRequest
import java.time.LocalDate
import java.util.*

data class TaskDto(
    var id: Int?,
    var username: String?,
    var task: String,
    var priority: Int,
    var status: Int,
    var description: String?,
    var planDate: LocalDate?,
    var doneDate: LocalDate?
) {

    constructor(task: Task): this(task.id, task.username, task.task, task.priority, task.status, task.description, task.planDate, task.doneDate)

    constructor(request: TaskRequest): this(null, null, request.task, request.priority,
        request.status, request.description, null, null) {
        if (request.status == 3) {
            this.doneDate = request.date
        } else {
            this.planDate = request.date
        }
    }
}