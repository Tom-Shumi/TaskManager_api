package com.ne.jp.shumipro_api.dto

import com.ne.jp.shumipro_api.entity.ZeroSecondThinkingContent
import java.time.LocalDate

data class ZeroSecondThinkingDto(
    var id: Int,
    var theme: String,
    var doneDate: LocalDate,
    var contentList: List<ZeroSecondThinkingContent>?
) {
    constructor(id: Int, theme: String, doneDate: LocalDate) : this(id, theme, doneDate, null)
}