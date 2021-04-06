package com.ne.jp.shumipro_api.entity

import com.ne.jp.shumipro_api.dto.TaskDto
import java.io.Serializable
import java.util.*

data class Task(
    var id: Int? = 0
    , var username: String? = ""
    , var task: String? = ""
    , var priority: Int? = 0
    , var status: Int? = 0
    , var description: String? = ""
    , var plan_date: Date? = null
    , var done_date: Date? = null
): Serializable{

    fun setTask(taskDto: TaskDto): Task {
        this.id = taskDto.id
        this.username = taskDto.username
        this.task = taskDto.task
        this.priority = taskDto.priority
        this.status = taskDto.status
        this.description = taskDto.description
        this.plan_date = taskDto.planDate
        this.done_date = taskDto.doneDate
        return this
    }
}
