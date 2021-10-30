package com.ne.jp.shumipro_api.response

import com.ne.jp.shumipro_api.dto.TaskCommentDto
import com.ne.jp.shumipro_api.dto.ZeroSecondThinkingDto
import com.ne.jp.shumipro_api.util.DateUtil

data class ZeroSecondThinkingResponse(
    var id: Int,) {

    constructor(dto: ZeroSecondThinkingDto):
            this(dto.id)
}