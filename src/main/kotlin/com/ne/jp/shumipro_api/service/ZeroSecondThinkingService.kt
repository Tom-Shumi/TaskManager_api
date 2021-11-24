package com.ne.jp.shumipro_api.service

import com.ne.jp.shumipro_api.Constants
import com.ne.jp.shumipro_api.Constants.Companion.ZERO_SECOND_THINKING_PREFIX_CONTENT
import com.ne.jp.shumipro_api.Constants.Companion.ZERO_SECOND_THINKING_PREFIX_WHY
import com.ne.jp.shumipro_api.dto.DailyTaskInfoDto
import com.ne.jp.shumipro_api.dto.ZeroSecondThinkingDto
import com.ne.jp.shumipro_api.entity.Task
import com.ne.jp.shumipro_api.entity.User
import com.ne.jp.shumipro_api.entity.ZeroSecondThinkingContent
import com.ne.jp.shumipro_api.entity.ZeroSecondThinkingTheme
import com.ne.jp.shumipro_api.mapper.UserMapper
import com.ne.jp.shumipro_api.mapper.ZeroSecondThinkingContentMapper
import com.ne.jp.shumipro_api.mapper.ZeroSecondThinkingThemeMapper
import com.nimbusds.oauth2.sdk.util.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
@Transactional
class ZeroSecondThinkingService {

    @Autowired
    lateinit var zeroSecondThinkingThemeMapper: ZeroSecondThinkingThemeMapper
    @Autowired
    lateinit var zeroSecondThinkingContentMapper: ZeroSecondThinkingContentMapper
    @Autowired
    lateinit var userMapper: UserMapper

    @Value("\${data.limit}")
    var limit: Int = 0

    fun getZeroSecondThinkingList(username: String, search: String?, nextKey: Int?): List<ZeroSecondThinkingDto> {
        val param = mapOf(
            "username" to username
            , "search" to search
            , "nextKey" to nextKey
            , "limit" to limit)
        val zeroSecondThinkingList = zeroSecondThinkingThemeMapper.getByUsernameAndNextKey(param)

        for (zeroSecondThinking in zeroSecondThinkingList) {
            zeroSecondThinking.contentList = zeroSecondThinkingContentMapper.getByThemeId(zeroSecondThinking.id)
        }

        return zeroSecondThinkingList
    }

    fun registerZeroSecondThinking(username: String, theme: String, contentList: List<String>): Int? {
        val userCheck: User? = userMapper.getUser(username)
        if (userCheck is User) {
            val zeroSecondThinkingTheme = ZeroSecondThinkingTheme(null, username, theme, LocalDate.now())
            zeroSecondThinkingThemeMapper.insert(zeroSecondThinkingTheme)

            val themeId = zeroSecondThinkingTheme.id!!

            for (content in contentList) {
                if (content != ZERO_SECOND_THINKING_PREFIX_CONTENT && content != ZERO_SECOND_THINKING_PREFIX_WHY) {
                    zeroSecondThinkingContentMapper.insert(ZeroSecondThinkingContent(null, themeId, content))
                }
            }

            return themeId
        }
        return null
    }

    fun deleteZeroSecondThinking(themeId: Int, username: String): Int {
        val themeCheck = zeroSecondThinkingThemeMapper.getById(themeId)
        return if (themeCheck is ZeroSecondThinkingTheme && themeCheck.username == username) {
            val deleteCount = zeroSecondThinkingThemeMapper.deleteById(themeId)
            zeroSecondThinkingContentMapper.deleteByThemeId(themeId)
            deleteCount
        } else {
            0
        }
    }

    fun updateZeroSecondThinkingTheme(themeId: Int, theme: String, username: String): Int {
        val themeCheck = zeroSecondThinkingThemeMapper.getById(themeId)
        return if (themeCheck is ZeroSecondThinkingTheme && themeCheck.username == username) {
            zeroSecondThinkingThemeMapper.updateById(themeId, theme)
        } else {
            0
        }
    }

    fun updateZeroSecondThinkingContent(themeId: Int, contentId: Int, content: String, isWhyText: Boolean, username: String): Int {
        val prefix = if (isWhyText) ZERO_SECOND_THINKING_PREFIX_WHY else ZERO_SECOND_THINKING_PREFIX_CONTENT;

        val contentCheck = zeroSecondThinkingContentMapper.getByIdAndThemeIdAndUsername(contentId, themeId, username)
        return if (contentCheck is ZeroSecondThinkingContent) {
            zeroSecondThinkingContentMapper.updateById(contentId, prefix + content)
        } else {
            0
        }
    }
}