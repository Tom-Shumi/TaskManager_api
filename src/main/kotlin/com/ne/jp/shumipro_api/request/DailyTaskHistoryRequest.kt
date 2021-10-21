package com.ne.jp.shumipro_api.request

import java.time.LocalDate
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

data class DailyTaskHistoryRequest(
    @field:NotNull(message="{e.001}")
    var dailyTaskId: Int,
    @field:NotNull(message="{e.001}")
    var doneTime: Int,
    @field:NotNull(message="{e.001}")
    @field:Min(value = 1, message="{e.003}")
    var quota: Int,
    var doneDate: LocalDate?
) {
}