package com.ne.jp.shumipro_api.dto

import com.ne.jp.shumipro_api.entity.Task
import com.ne.jp.shumipro_api.request.TaskRequest
import com.ne.jp.shumipro_api.request.UserRequest
import java.time.LocalDate
import java.util.*

data class TaskDto(
    var id: Int? = 0
    , var username: String? = ""
    , var task: String? = ""
    , var priority: Int? = 0
    , var status: Int? = 0
    , var description: String? = ""
    , var planDate: LocalDate? = null
    , var doneDate: LocalDate? = null
) {

    fun setTaskDto(task: Task): TaskDto{
        this.id = task.id
        this.username = task.username
        this.task = task.task
        this.priority = task.priority
        this.status = task.status
        this.description = task.description
        this.planDate = task.plan_date
        this.doneDate = task.done_date
        return this
    }
    fun setTaskDto(taskRequest: TaskRequest): TaskDto{
        this.task = taskRequest.task
        this.priority = taskRequest.priority
        this.status = taskRequest.status
        this.description = taskRequest.description
        if (taskRequest.status == 3) {
            this.doneDate = taskRequest.date
        } else {
            this.planDate = taskRequest.date
        }

        return this
    }
}