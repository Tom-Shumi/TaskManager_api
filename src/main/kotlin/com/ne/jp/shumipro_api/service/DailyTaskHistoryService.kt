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

    fun registerDailyTaskHistory(dto: DailyTaskHistoryDto): DailyTaskHistoryDto {

        // TODO デイリータスクがあるか確認。なければエラーを返却する。

        val currentDate = LocalDate.now()
        val current = dailyTaskHistoryMapper.getByDailyTaskIdAndDoneDate(dto.dailyTaskId, currentDate)
        return if (current is DailyTaskHistory) {
            // update
            current.done_time += dto.doneTime
            dailyTaskHistoryMapper.update(current)
            DailyTaskHistoryDto(current)
        } else {
            // insert
            val dailyTaskHistory = DailyTaskHistory(dto.dailyTaskId, currentDate, dto.doneTime, dto.quota)
            dailyTaskHistoryMapper.insert(dailyTaskHistory)
            DailyTaskHistoryDto(dailyTaskHistory)
        }
    }
}