package com.ne.jp.shumipro_api.response

import com.ne.jp.shumipro_api.dto.DailyTaskHistoryDto
import com.ne.jp.shumipro_api.dto.TaskDto
import com.ne.jp.shumipro_api.util.DateUtil
import java.util.*

data class DailyTaskHistoryResponse(
    var id: Int? = null
    , var dailyTaskId: Int? = null
    , var doneDate: Date? = null
    , var doneTime: Int? = null
    , var quota: Int? = null
) {

    fun setDailyTaskHistoryResponse(dailyTaskHistoryDto: DailyTaskHistoryDto): DailyTaskHistoryResponse{
        this.id = dailyTaskHistoryDto.id
        this.dailyTaskId = dailyTaskHistoryDto.dailyTaskId
        this.doneDate = dailyTaskHistoryDto.doneDate
        this.doneTime = dailyTaskHistoryDto.doneTime
        this.quota = dailyTaskHistoryDto.quota
        return this
    }

}