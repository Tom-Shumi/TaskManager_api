package com.ne.jp.shumipro_api.entity

import com.ne.jp.shumipro_api.dto.DailyTaskHistoryDto
import java.time.LocalDate
import java.util.*

data class DailyTaskHistory(
    var id: Int?
    , var daily_task_id: Int
    , var done_date: LocalDate
    , var done_time: Int
    , var quota: Int
) {

    constructor(daily_task_id: Int, done_date: LocalDate, done_time: Int, quota: Int)
            : this(id =null, daily_task_id, done_date, done_time, quota)
}