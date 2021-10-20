package com.ne.jp.shumipro_api.entity

import com.ne.jp.shumipro_api.dto.TaskDto
import java.io.Serializable
import java.time.LocalDate
import java.util.*

data class Task(
    var id: Int? = 0
    , var username: String? = ""
    , var task: String? = ""
    , var priority: Int? = 0
    , var status: Int? = 0
    , var description: String? = ""
    , var planDate: LocalDate? = null
    , var doneDate: LocalDate? = null
): Serializable{

    fun setTask(taskDto: TaskDto): Task {
        this.id = taskDto.id
        this.username = taskDto.username
        this.task = taskDto.task
        this.priority = taskDto.priority
        this.status = taskDto.status
        this.description = taskDto.description
        this.planDate = taskDto.planDate
        this.doneDate = taskDto.doneDate
        return this
    }
}
