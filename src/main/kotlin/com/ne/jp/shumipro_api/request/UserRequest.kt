package com.ne.jp.shumipro_api.request

import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull


data class UserRequest(
    val username: String?,
    @field:NotNull(message="{e.001}")
    val password: String?,
    @field:NotNull(message="{e.001}")
    @field:Min(value = 0, message="{e.002}")
    @field:Max(value = 1, message="{e.002}")
    val enabledflg: Int?,
    @field:NotNull(message="{e.001}")
    @field:Min(value = 0, message="{e.002}")
    @field:Max(value = 1, message="{e.002}")
    val adminflg: Int?
) {
}