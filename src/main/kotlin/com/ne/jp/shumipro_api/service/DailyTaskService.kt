package com.ne.jp.shumipro_api.service

import com.ne.jp.shumipro_api.dto.DailyTaskInfoDto
import com.ne.jp.shumipro_api.mapper.DailyTaskMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
@Transactional
class DailyTaskService {

    @Autowired
    lateinit var dailyTaskMapper: DailyTaskMapper

    fun getDailyTaskList(username: String): List<DailyTaskInfoDto> {
        return dailyTaskMapper.getDailyTaskInfoByUsername(username, LocalDate.now())
    }
}