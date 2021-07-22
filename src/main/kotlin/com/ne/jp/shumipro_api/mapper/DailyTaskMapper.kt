package com.ne.jp.shumipro_api.mapper

import com.ne.jp.shumipro_api.dto.DailyTaskInfoDto
import com.ne.jp.shumipro_api.entity.DailyTask
import com.ne.jp.shumipro_api.entity.Task
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import java.time.LocalDate
import java.util.*

@Mapper
interface DailyTaskMapper {

    fun getDailyTaskInfoByUsername(@Param("username") username: String, @Param("doneDate") doneDate: LocalDate,
                                   @Param("includeDeleteFlg") includeDeleteFlg: Boolean): List<DailyTaskInfoDto>

    fun getDailyTaskById(@Param("id") id: Int): DailyTask?

    fun insert(dailyTask: DailyTask)
}