package com.ne.jp.shumipro_api.response

import com.ne.jp.shumipro_api.dto.DailyTaskHistoryDto
import java.time.LocalDate

data class DailyTaskHistoryResponse(
    var id: Int
    , var dailyTaskId: Int
    , var doneDate: LocalDate
    , var doneTime: Int
    , var quota: Int
) {

    constructor(dto: DailyTaskHistoryDto)
            : this(dto.id!!, dto.dailyTaskId, dto.doneDate!!, dto.doneTime, dto.quota)

}