package com.ne.jp.shumipro_api.dto

import java.util.*

data class TaskCommentDto(
    var id: Int?
    , var taskId: Int?
    , var username: String?
    , var comment: String?
    , var createDate: Date?
) {
}