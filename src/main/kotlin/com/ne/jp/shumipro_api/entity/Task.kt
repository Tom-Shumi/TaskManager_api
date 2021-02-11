package com.ne.jp.shumipro_api.entity

import java.io.Serializable

data class Task(
    var id: Int?
    , var username: String?
    , var task: String?
): Serializable
