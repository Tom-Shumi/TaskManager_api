package com.ne.jp.shumipro_api.request

import javax.validation.constraints.NotNull

data class ZeroSecondThinkingRequest(
    @field:NotNull(message="{e.001}")
    var theme: String,
    @field:NotNull(message="{e.001}")
    var contentList: List<String>
)