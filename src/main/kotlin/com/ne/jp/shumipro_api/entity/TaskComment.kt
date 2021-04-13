package com.ne.jp.shumipro_api.entity

import java.util.*

data class TaskComment(
    var id: Int?
    , var taskId: Int?
    , var username: String?
    , var comment: String?
    , var createDate: Date?
) {
}