package com.ne.jp.shumipro_api.entity

import com.ne.jp.shumipro_api.dto.TaskDto
import java.io.Serializable

data class Task(
    var id: Int? = 0
    , var username: String? = ""
    , var task: String? = ""
): Serializable{

    fun setTask(taskDto: TaskDto): Task {
        this.id = taskDto.id
        this.username = taskDto.username
        this.task = taskDto.task
        return this
    }
}
