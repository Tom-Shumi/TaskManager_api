package com.ne.jp.shumipro_api.service

import com.ne.jp.shumipro_api.dto.DailyTaskHistoryDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class DailyTaskHistoryService {

    fun registerDailyTaskHistory(dailyTaskHistoryDto: DailyTaskHistoryDto): DailyTaskHistoryDto? {
        // TODO 実装
        return null
    }
}