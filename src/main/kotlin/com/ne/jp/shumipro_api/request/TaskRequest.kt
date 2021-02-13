package com.ne.jp.shumipro_api.request

import javax.validation.constraints.NotNull

data class TaskRequest(
    @field:NotNull(message="{e.001}")
    var username: String?,
    @field:NotNull(message="{e.001}")
    var task: String?
) {
}