package com.ne.jp.shumipro_api.mapper

import com.ne.jp.shumipro_api.dto.ZeroSecondThinkingDto
import com.ne.jp.shumipro_api.entity.ZeroSecondThinkingTheme
import org.apache.ibatis.annotations.Delete
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select

@Mapper
interface ZeroSecondThinkingThemeMapper {

    fun getByUsernameAndNextKey(param: Map<String, Any?>): List<ZeroSecondThinkingDto>

    fun insert(zeroSecondThinkingTheme: ZeroSecondThinkingTheme)

    @Select("SELECT * FROM zero_second_thinking_theme WHERE id = #{id}")
    fun getById(id: Int): ZeroSecondThinkingTheme?

    @Delete("DELETE FROM zero_second_thinking_theme WHERE id = #{id}")
    fun deleteById(id: Int): Int
}