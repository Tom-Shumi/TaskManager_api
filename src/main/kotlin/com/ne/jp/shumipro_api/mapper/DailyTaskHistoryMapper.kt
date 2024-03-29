package com.ne.jp.shumipro_api.mapper

import com.ne.jp.shumipro_api.dto.DailyTaskHistoryInfoDto
import com.ne.jp.shumipro_api.dto.DailyTaskInfoDto
import com.ne.jp.shumipro_api.entity.DailyTaskHistory
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import java.time.LocalDate

@Mapper
interface DailyTaskHistoryMapper {

    fun getByDailyTaskIdAndDoneDate(@Param("dailyTaskId") dailyTaskId: Int, @Param("doneDate") doneDate: LocalDate): DailyTaskHistory?

    fun getDailyTaskHistory(@Param("username") username: String, @Param("targetDate") targetDate: LocalDate): List<DailyTaskHistoryInfoDto>

    fun insert(dailyTaskHistory: DailyTaskHistory)

    fun update(dailyTaskHistory: DailyTaskHistory): Int
}