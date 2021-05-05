package com.ne.jp.shumipro_api.dto

data class TaskGraphDto(
    var planTask : List<Map<String, Int>>? = null
    , var doneTask : List<Map<String, Int>>? = null
    , var comment : List<Map<String, Int>>? = null
) {
}