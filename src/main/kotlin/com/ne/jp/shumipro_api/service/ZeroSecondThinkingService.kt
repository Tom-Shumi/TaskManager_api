package com.ne.jp.shumipro_api.service

import com.ne.jp.shumipro_api.dto.DailyTaskInfoDto
import com.ne.jp.shumipro_api.dto.ZeroSecondThinkingDto
import com.ne.jp.shumipro_api.mapper.UserMapper
import com.ne.jp.shumipro_api.mapper.ZeroSecondThinkingContentMapper
import com.ne.jp.shumipro_api.mapper.ZeroSecondThinkingThemeMapper
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

    fun getZeroSecondThinkingList(username: String, nextKey: Int?): List<ZeroSecondThinkingDto> {
        val param = mapOf(
            "username" to username
            , "nextKey" to nextKey
            , "limit" to limit)
        val zeroSecondThinkingList = zeroSecondThinkingThemeMapper.getByUsernameAndNextKey(param)

        return zeroSecondThinkingList
    }
}