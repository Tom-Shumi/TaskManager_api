package com.ne.jp.shumipro_api.mapper

import com.ne.jp.shumipro_api.dto.ZeroSecondThinkingDto
import org.apache.ibatis.annotations.Mapper

@Mapper
interface ZeroSecondThinkingThemeMapper {

    fun getByUsernameAndNextKey(param: Map<String, Any?>): List<ZeroSecondThinkingDto>
}