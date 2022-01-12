package com.ne.jp.shumipro_api.dto

import java.time.LocalDate

data class LearningInfoDto(
    var id: Int?,
    var username: String,
    var categoryId: Int,
    var content: String,
    var referenceUrl: String?,
    var createDate: LocalDate,
    var categoryName: String
    )