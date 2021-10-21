package com.ne.jp.shumipro_api.response

import com.ne.jp.shumipro_api.dto.TaskDto
import com.ne.jp.shumipro_api.dto.TaskGraphDto
import com.ne.jp.shumipro_api.util.DateUtil

data class TaskGraphResponse(
    var planTask : List<Map<String, Int>>?,
    var doneTask : List<Map<String, Int>>?,
    var comment : List<Map<String, Int>>?
) {

    constructor(dto: TaskGraphDto): this(dto.planTask, dto.doneTask, dto.comment)
}