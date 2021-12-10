package com.ne.jp.shumipro_api.service

import com.ne.jp.shumipro_api.Constants
import com.ne.jp.shumipro_api.Constants.Companion.ZERO_SECOND_THINKING_PREFIX_CONTENT
import com.ne.jp.shumipro_api.Constants.Companion.ZERO_SECOND_THINKING_PREFIX_WHY
import com.ne.jp.shumipro_api.dto.DailyTaskInfoDto
import com.ne.jp.shumipro_api.dto.EsZeroSecondThinkingDocumentDto
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
    lateinit var esZeroSecondThinkingService: EsZeroSecondThinkingService
    @Autowired
    lateinit var userMapper: UserMapper

    @Value("\${data.limit}")
    var limit: Int = 0

    fun getZeroSecondThinkingListFromDb(username: String, search: String?, nextKey: Int?): List<ZeroSecondThinkingDto> {
        val param = mapOf(
            "username" to username
            , "search" to search
            , "nextKey" to nextKey
            , "limit" to limit)

        val zeroSecondThinkingList = zeroSecondThinkingThemeMapper.getByUsernameAndNextKey(param)
        fetchAndSetZeroSecondContentList(zeroSecondThinkingList)

        return zeroSecondThinkingList
    }

    fun getZeroSecondThinkingListFromEs(username: String, search: String, nextKey: Int?): List<ZeroSecondThinkingDto> {
        val esZeroSecondThinkingListMap = esZeroSecondThinkingService.searchZeroSecondThinkingDocument(username, search)

        val contentList = esZeroSecondThinkingListMap[Constants.ES_INDEX_NAME_CONTENT]
        val themeIdListFromContent = if (contentList is List<EsZeroSecondThinkingDocumentDto>) contentList.map { c -> c.id }.toList() else listOf()
        val themeList = esZeroSecondThinkingListMap[Constants.ES_INDEX_NAME_THEME]
        val themeIdList = if (themeList is List<EsZeroSecondThinkingDocumentDto>) themeList.map { t -> t.id }.toList() else listOf()

        val joinedThemeIdList = (themeIdListFromContent + themeIdList).distinct()
        if (joinedThemeIdList.isEmpty()) {
            return emptyList()
        }

        val zeroSecondThinkingList = zeroSecondThinkingThemeMapper.getByIdListAndNextKey(joinedThemeIdList, nextKey, limit)
        fetchAndSetZeroSecondContentList(zeroSecondThinkingList)
        
        return zeroSecondThinkingList
    }

    private fun fetchAndSetZeroSecondContentList(dtoList: List<ZeroSecondThinkingDto>) {
        for (zeroSecondThinking in dtoList) {
            zeroSecondThinking.contentList = zeroSecondThinkingContentMapper.getByThemeId(zeroSecondThinking.id)
        }
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