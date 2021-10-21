package com.ne.jp.shumipro_api.entity

import com.ne.jp.shumipro_api.dto.TaskDto
import java.io.Serializable
import java.time.LocalDate
import java.util.*

data class Task(
    var id: Int?,
    var username: String?,
    var task: String,
    var priority: Int,
    var status: Int,
    var description: String?,
    var planDate: LocalDate?,
    var doneDate: LocalDate?
): Serializable{

    constructor(dto: TaskDto): this(dto.id, dto.username, dto.task, dto.priority, dto.status, dto.description, dto.planDate, dto.doneDate)
}
