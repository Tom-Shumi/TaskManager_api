package com.ne.jp.shumipro_api.request

import javax.validation.constraints.NotNull

data class TokenRequest(
    @field:NotNull(message="{e.001}")
    val username: String,
    @field:NotNull(message="{e.001}")
    val password: String
) {
}