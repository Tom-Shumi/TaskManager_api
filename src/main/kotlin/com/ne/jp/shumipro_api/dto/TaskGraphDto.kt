package com.ne.jp.shumipro_api.dto

data class TaskGraphDto(
    var planTask : List<Map<String, Int>>?,
    var doneTask : List<Map<String, Int>>?,
    var comment : List<Map<String, Int>>?
) {
}