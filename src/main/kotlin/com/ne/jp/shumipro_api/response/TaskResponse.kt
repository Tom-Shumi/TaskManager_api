package com.ne.jp.shumipro_api.response

import com.ne.jp.shumipro_api.dto.TaskDto

data class TaskResponse(
    var id: Int? = 0
    , var username: String? = ""
    , var task: String? = ""
    , var priority: Int? = 0
    , var status: Int? = 0
) {

    fun setTaskResponse(taskDto: TaskDto): TaskResponse{
        this.id = taskDto.id
        this.username = taskDto.username
        this.task = taskDto.task
        this.priority = taskDto.priority
        this.status = taskDto.status
        return this
    }
}