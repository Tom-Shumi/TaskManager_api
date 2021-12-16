package com.ne.jp.shumipro_api.entity

import java.time.LocalDate

data class Learning(
    var id: Int?,
    var username: String,
    var categoryId: Int,
    var content: String,
    var referenceUrl: String?,
    var createDate: LocalDate
)