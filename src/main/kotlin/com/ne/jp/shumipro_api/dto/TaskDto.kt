package com.ne.jp.shumipro_api.dto

import com.ne.jp.shumipro_api.entity.Task
import com.ne.jp.shumipro_api.request.UserRequest

data class TaskDto(
    var id: Int? = 0
    , var username: String? = ""
    , var task: String? = ""
) {

    fun setTaskDto(task: Task): TaskDto{
        this.id = task.id
        this.username = task.username
        this.task = task.task
        return this
    }

}