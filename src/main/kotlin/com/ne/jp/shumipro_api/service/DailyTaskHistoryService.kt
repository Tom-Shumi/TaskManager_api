package com.ne.jp.shumipro_api.service

import com.ne.jp.shumipro_api.dto.DailyTaskHistoryDto
import com.ne.jp.shumipro_api.dto.DailyTaskHistoryInfoDto
import com.ne.jp.shumipro_api.entity.DailyTask
import com.ne.jp.shumipro_api.entity.DailyTaskHistory
import com.ne.jp.shumipro_api.mapper.DailyTaskHistoryMapper
import com.ne.jp.shumipro_api.mapper.DailyTaskMapper
import com.ne.jp.shumipro_api.util.DateUtil
import com.ne.jp.shumipro_api.util.StringUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.StringUtils
import java.time.LocalDate

@Service
@Transactional
class DailyTaskHistoryService {

    @Autowired
    lateinit var dailyTaskHistoryMapper: DailyTaskHistoryMapper
    @Autowired
    lateinit var dailyTaskMapper: DailyTaskMapper

    private fun registerDailyTaskHistory(dto: DailyTaskHistoryDto, doneDate: LocalDate): DailyTaskHistoryDto? {

        val dailyTask = dailyTaskMapper.findById(dto.dailyTaskId)
        if (dailyTask !is DailyTask) return null

        val current = dailyTaskHistoryMapper.getByDailyTaskIdAndDoneDate(dto.dailyTaskId, doneDate)
        return if (current is DailyTaskHistory) {
            // update
            current.done_time += dto.doneTime
            if (current.done_time < 0) current.done_time = 0

            dailyTaskHistoryMapper.update(current)
            DailyTaskHistoryDto(current)
        } else {
            // insert
            if (dto.doneTime < 0) dto.doneTime = 0

            val dailyTaskHistory = DailyTaskHistory(dto.dailyTaskId, doneDate, dto.doneTime, dto.quota)
            dailyTaskHistoryMapper.insert(dailyTaskHistory)
            DailyTaskHistoryDto(dailyTaskHistory)
        }
    }

    fun registerTodayDailyTaskHistory(dto: DailyTaskHistoryDto): DailyTaskHistoryDto? {
        return registerDailyTaskHistory(dto, LocalDate.now())
    }

    fun registerLaterDailyTaskHistory(dto: DailyTaskHistoryDto): DailyTaskHistoryDto? {
        return registerDailyTaskHistory(dto, dto.doneDate!!)
    }

    fun getDailyTaskHistory(username: String, nextTargetDateStr: String?): List<List<DailyTaskHistoryInfoDto>>? {

        val nextTargetDate: LocalDate = if (StringUtil.isEmpty(nextTargetDateStr)) {
            LocalDate.now();
        } else {
            DateUtil.toLocalDateNonDelimiterYYYYMMDD(nextTargetDateStr) ?: return null
        }

        val responseList: MutableList<List<DailyTaskHistoryInfoDto>> = mutableListOf()

        for (i in 0 until 5) {
            val tempDate = nextTargetDate.minusDays(i.toLong())
            responseList.add(dailyTaskHistoryMapper.getDailyTaskHistory(username, tempDate))
        }

        return responseList;
    }
}