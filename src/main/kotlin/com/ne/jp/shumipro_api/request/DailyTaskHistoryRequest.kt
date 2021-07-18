package com.ne.jp.shumipro_api.request

import java.util.*
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

data class DailyTaskHistoryRequest(
    @field:NotNull(message="{e.001}")
    var daily_task_id: Int,
    @field:NotNull(message="{e.001}")
    var done_time: Int,
    @field:NotNull(message="{e.001}")
    @field:Min(value = 1, message="{e.003}")
    var quota: Int,
) {
}