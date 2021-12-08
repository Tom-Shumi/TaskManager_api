package com.ne.jp.shumipro_api.mapper

import com.ne.jp.shumipro_api.dto.ZeroSecondThinkingDto
import com.ne.jp.shumipro_api.entity.ZeroSecondThinkingContent
import com.ne.jp.shumipro_api.entity.ZeroSecondThinkingTheme
import org.apache.ibatis.annotations.*

@Mapper
interface ZeroSecondThinkingContentMapper {

    fun getByThemeId(themeId: Int): List<ZeroSecondThinkingContent>

    fun insert(zeroSecondThinkingContent: ZeroSecondThinkingContent)

    @Delete("DELETE FROM zero_second_thinking_content WHERE theme_id = #{themeId}")
    fun deleteByThemeId(themeId: Int): Int

    @Select("SELECT c.* " +
            "FROM zero_second_thinking_content c " +
            "INNER JOIN zero_second_thinking_theme t " +
            "ON c.theme_id = t.id " +
            "WHERE c.id = #{id} " +
            "AND c.theme_id = #{themeId} " +
            "AND t.username = #{username}")
    fun getByIdAndThemeIdAndUsername(@Param("id") id: Int, @Param("themeId") themeId: Int, @Param("username") username: String): ZeroSecondThinkingContent?

    @Update("UPDATE zero_second_thinking_content SET content = #{content} WHERE id = #{id}")
    fun updateById(@Param("id") id: Int, @Param("content") content: String): Int

    fun getByIdList(@Param("idList") idList: List<Int>): List<ZeroSecondThinkingContent>
}