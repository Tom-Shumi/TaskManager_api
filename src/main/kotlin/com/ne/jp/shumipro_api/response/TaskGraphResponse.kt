package com.ne.jp.shumipro_api.response

data class TaskGraphResponse(
    var planTask : List<Map<String, Integer>>
    , var doneTask : List<Map<String, Integer>>
    , var comment : List<Map<String, Integer>>
) {
}