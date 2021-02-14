package com.ne.jp.shumipro_api.dto

import com.ne.jp.shumipro_api.entity.Task
import com.ne.jp.shumipro_api.request.TaskRequest
import com.ne.jp.shumipro_api.request.UserRequest

data class TaskDto(
    var id: Int? = 0
    , var username: String? = ""
    , var task: String? = ""
    , var priority: Int? = 0
    , var status: Int? = 0
) {

    fun setTaskDto(task: Task): TaskDto{
        this.id = task.id
        this.username = task.username
        this.task = task.task
        this.priority = task.priority
        this.status = task.status
        return this
    }
    fun setTaskDto(taskRequest: TaskRequest): TaskDto{
        this.task = taskRequest.task
        this.priority = taskRequest.priority
        this.status = taskRequest.status
        return this
    }
}