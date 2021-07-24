package com.ne.jp.shumipro_api.response

import com.ne.jp.shumipro_api.dto.DailyTaskHistoryInfoDto
import com.ne.jp.shumipro_api.util.DateUtil
import java.time.LocalDate

data class DailyTaskHistoryInfoResponse (
    var dailyTaskId: Int
    , var title: String
    , var doneDate: String?
    , var doneTime: Int?
    , var quota: Int?
    , var doneFlg: Int
) {

    constructor(dto: DailyTaskHistoryInfoDto): this(dto.dailyTaskId, dto.title, null, dto.doneTime, dto.quota, dto.doneFlg) {
        this.doneDate = DateUtil.toStringYYYYMMDD(dto.doneDate)
    }
}