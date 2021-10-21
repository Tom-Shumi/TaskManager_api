package com.ne.jp.shumipro_api.request

import java.time.LocalDate
import java.util.*
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

data class TaskRequest(
    @field:NotNull(message="{e.001}")
    var task: String,
    @field:NotNull(message="{e.001}")
    @field:Min(value = 1, message="{e.003}")
    @field:Max(value = 5, message="{e.003}")
    var priority: Int,
    @field:NotNull(message="{e.001}")
    @field:Min(value = 1, message="{e.003}")
    @field:Max(value = 3, message="{e.003}")
    var status: Int,
    var description: String?,
    var date: LocalDate?
) {
}