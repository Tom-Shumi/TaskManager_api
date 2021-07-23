package com.ne.jp.shumipro_api.service

import com.ne.jp.shumipro_api.dto.DailyTaskHistoryDto
import com.ne.jp.shumipro_api.entity.DailyTask
import com.ne.jp.shumipro_api.entity.DailyTaskHistory
import com.ne.jp.shumipro_api.mapper.DailyTaskHistoryMapper
import com.ne.jp.shumipro_api.mapper.DailyTaskMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
@Transactional
class DailyTaskHistoryService {

    @Autowired
    lateinit var dailyTaskHistoryMapper: DailyTaskHistoryMapper
    @Autowired
    lateinit var dailyTaskMapper: DailyTaskMapper

    fun registerDailyTaskHistory(dto: DailyTaskHistoryDto): DailyTaskHistoryDto? {

        val dailyTask = dailyTaskMapper.findById(dto.dailyTaskId)
        if (dailyTask !is DailyTask) return null

        val currentDate = LocalDate.now()
        val current = dailyTaskHistoryMapper.getByDailyTaskIdAndDoneDate(dto.dailyTaskId, currentDate)
        return if (current is DailyTaskHistory) {
            // update
            current.done_time += dto.doneTime
            if (current.done_time < 0) current.done_time = 0

            dailyTaskHistoryMapper.update(current)
            DailyTaskHistoryDto(current)
        } else {
            // insert
            if (dto.doneTime < 0) dto.doneTime = 0

            val dailyTaskHistory = DailyTaskHistory(dto.dailyTaskId, currentDate, dto.doneTime, dto.quota)
            dailyTaskHistoryMapper.insert(dailyTaskHistory)
            DailyTaskHistoryDto(dailyTaskHistory)
        }
    }
}