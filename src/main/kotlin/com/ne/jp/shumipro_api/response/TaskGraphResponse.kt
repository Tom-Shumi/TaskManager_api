package com.ne.jp.shumipro_api.response

import com.ne.jp.shumipro_api.dto.TaskDto
import com.ne.jp.shumipro_api.dto.TaskGraphDto
import com.ne.jp.shumipro_api.util.DateUtil

data class TaskGraphResponse(
    var planTask : List<Map<String, Int>>? = null
    , var doneTask : List<Map<String, Int>>? = null
    , var comment : List<Map<String, Int>>? = null
) {
    fun setTaskGraphResponse(taskGraphDto: TaskGraphDto): TaskGraphResponse{
        this.planTask = taskGraphDto.planTask
        this.doneTask = taskGraphDto.doneTask
        this.comment = taskGraphDto.comment
        return this
    }
}