package com.ne.jp.shumipro_api.request

import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

data class TaskStatusRequest(
    @field:NotNull(message="{e.001}")
    @field:Min(value = 1, message="{e.003}")
    @field:Max(value = 3, message="{e.003}")
    var status: Int?
) {
}