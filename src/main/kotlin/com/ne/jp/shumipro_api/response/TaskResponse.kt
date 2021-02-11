package com.ne.jp.shumipro_api.response

import com.ne.jp.shumipro_api.dto.TaskDto

data class TaskResponse(
    var id: Int? = 0
    , var username: String? = ""
    , var task: String? = ""
) {

    fun setTaskResponse(taskDto: TaskDto): TaskResponse{
        this.id = taskDto.id
        this.username = taskDto.username
        var task = taskDto.task
        return this
    }
}