package com.ne.jp.shumipro_api.response

import com.ne.jp.shumipro_api.dto.TaskCommentDto
import com.ne.jp.shumipro_api.dto.ZeroSecondThinkingDto
import com.ne.jp.shumipro_api.entity.ZeroSecondThinkingContent
import com.ne.jp.shumipro_api.util.DateUtil
import java.time.LocalDate

data class ZeroSecondThinkingResponse(
    var id: Int,
    var theme: String,
    var doneDate: LocalDate,
    var contentList: List<ZeroSecondThinkingContent>?
) {

    constructor(dto: ZeroSecondThinkingDto):
            this(dto.id, dto.theme, dto.doneDate, dto.contentList)
}