package com.ne.jp.shumipro_api.dto

import com.ne.jp.shumipro_api.entity.DailyTaskHistory
import com.ne.jp.shumipro_api.entity.Task
import com.ne.jp.shumipro_api.request.DailyTaskHistoryRequest
import java.time.LocalDate
import java.util.*

data class DailyTaskHistoryDto(
    var id: Int?,
    var dailyTaskId: Int,
    var doneDate: LocalDate?,
    var doneTime: Int,
    var quota: Int
)  {

    constructor(dailyTaskHistory: DailyTaskHistory)
            : this(dailyTaskHistory.id, dailyTaskHistory.dailyTaskId, dailyTaskHistory.doneDate,
                    dailyTaskHistory.doneTime, dailyTaskHistory.quota)

    constructor(request: DailyTaskHistoryRequest)
            : this(null, request.dailyTaskId, request.doneDate,
                    request.doneTime, request.quota)

}