package com.ne.jp.shumipro_api.dto

import com.ne.jp.shumipro_api.entity.Task
import com.ne.jp.shumipro_api.request.DailyTaskHistoryRequest
import java.util.*

data class DailyTaskHistoryDto(
    var id: Int? = null
    , var dailyTaskId: Int? = null
    , var doneDate: Date? = null
    , var doneTime: Int? = null
    , var quota: Int? = null
)  {

    fun setDailyTaskHistoryDto(dailyTaskHistoryRequest: DailyTaskHistoryRequest): DailyTaskHistoryDto{
        this.dailyTaskId = dailyTaskHistoryRequest.daily_task_id
        this.doneTime = dailyTaskHistoryRequest.done_time
        this.quota = dailyTaskHistoryRequest.quota
        return this
    }

}