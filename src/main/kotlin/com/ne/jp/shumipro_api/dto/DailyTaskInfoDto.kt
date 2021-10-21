package com.ne.jp.shumipro_api.dto

import java.time.LocalDate

data class DailyTaskInfoDto(
    var id: Int,
    var username: String,
    var title: String,
    var description: String?,
    var priority: Int,
    var quota: Int,
    var deleteFlg: Int,
    var createDate: LocalDate,
    var deleteDate: LocalDate?,
    var doneDate: LocalDate?,
    var doneTime: Int?,
    var dispOrder: Int?
) {

}