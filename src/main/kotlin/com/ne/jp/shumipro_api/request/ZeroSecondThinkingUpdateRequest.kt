package com.ne.jp.shumipro_api.request

import javax.validation.constraints.NotNull

data class ZeroSecondThinkingUpdateRequest(
    @field:NotNull(message="{e.001}")
    var updateText: String,
    var isWhyText: Boolean = false
)