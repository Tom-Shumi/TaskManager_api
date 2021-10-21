package com.ne.jp.shumipro_api.entity

import com.ne.jp.shumipro_api.dto.DailyTaskHistoryDto
import java.time.LocalDate
import java.util.*

data class DailyTaskHistory(
    var id: Int?,
    var dailyTaskId: Int,
    var doneDate: LocalDate,
    var doneTime: Int,
    var quota: Int
) {

    constructor(dailyTaskId: Int, doneDate: LocalDate, doneTime: Int, quota: Int)
            : this(null, dailyTaskId, doneDate, doneTime, quota)
}