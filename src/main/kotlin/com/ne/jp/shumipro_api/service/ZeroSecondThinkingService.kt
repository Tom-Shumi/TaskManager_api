package com.ne.jp.shumipro_api.service

import com.ne.jp.shumipro_api.dto.DailyTaskInfoDto
import com.ne.jp.shumipro_api.dto.ZeroSecondThinkingDto
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
                if (content != "・" && content != "　　[なぜ？]") {
                    zeroSecondThinkingContentMapper.insert(ZeroSecondThinkingContent(null, themeId, content))
                }
            }

            return themeId
        }
        return null
    }
}