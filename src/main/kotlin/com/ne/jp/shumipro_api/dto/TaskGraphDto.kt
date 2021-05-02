package com.ne.jp.shumipro_api.dto

data class TaskGraphDto(
    var planTask : List<Map<String, Integer>>? = null
    , var doneTask : List<Map<String, Integer>>? = null
    , var comment : List<Map<String, Integer>>? = null
) {
}