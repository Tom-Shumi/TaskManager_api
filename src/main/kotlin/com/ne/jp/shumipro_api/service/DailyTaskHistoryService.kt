package com.ne.jp.shumipro_api.service

import com.ne.jp.shumipro_api.dto.DailyTaskHistoryDto
import com.ne.jp.shumipro_api.entity.DailyTaskHistory
import com.ne.jp.shumipro_api.mapper.DailyTaskHistoryMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
@Transactional
class DailyTaskHistoryService {

    @Autowired
    lateinit var dailyTaskHistoryMapper: DailyTaskHistoryMapper

    fun registerDailyTaskHistory(dto: DailyTaskHistoryDto): DailyTaskHistoryDto? {
        val currentDate = LocalDate.now()
        val current = dailyTaskHistoryMapper.getByDailyTaskIdAndDoneDate(dto.dailyTaskId!!, currentDate)
        if (current is DailyTaskHistory) {
            // 更新処理
        } else {
            // 登録処理
        }
        return null
    }
}