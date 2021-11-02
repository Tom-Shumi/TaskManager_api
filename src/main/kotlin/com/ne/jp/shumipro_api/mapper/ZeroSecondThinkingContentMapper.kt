package com.ne.jp.shumipro_api.mapper

import com.ne.jp.shumipro_api.dto.ZeroSecondThinkingDto
import com.ne.jp.shumipro_api.entity.ZeroSecondThinkingContent
import com.ne.jp.shumipro_api.entity.ZeroSecondThinkingTheme
import org.apache.ibatis.annotations.Mapper

@Mapper
interface ZeroSecondThinkingContentMapper {

    fun getByThemeId(themeId: Int): List<ZeroSecondThinkingContent>

    fun insert(zeroSecondThinkingContent: ZeroSecondThinkingContent)
}