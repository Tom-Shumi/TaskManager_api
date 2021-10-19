package com.ne.jp.shumipro_api.request

import java.time.LocalDate
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

data class DailyTaskRequest (

    @field:NotNull(message="{e.001}")
    var title: String,

    var description: String?,

    @field:NotNull(message="{e.001}")
    @field:Min(value = 1, message="{e.003}")
    @field:Max(value = 3, message="{e.003}")
    var priority: Int,

    @field:NotNull(message="{e.001}")
    @field:Min(value = 0, message="{e.003}")
    var quota: Int,

    @field:NotNull(message="{e.001}")
    @field:Min(value = 0, message="{e.003}")
    @field:Max(value = 1, message="{e.003}")
    var deleteFlg: Int,

    var createDate: String?,
    var deleteDate: String?,
) {
}