package com.ne.jp.shumipro_api.dto

import java.time.LocalDate

data class DailyTaskHistoryInfoDto (
    var dailyTaskId: Int,
    var title: String,
    var doneDate: LocalDate?,
    var doneTime: Int?,
    var quota: Int?,
    var doneFlg: Int
) {
}
