package com.ne.jp.shumipro_api.entity

import java.util.*

data class DailyTaskHistory(
    var id: Int? = null
    , var daily_task_id: Int? = null
    , var done_date: Date? = null
    , var done_time: Int? = null
    , var quota: Int? = null
) {

}