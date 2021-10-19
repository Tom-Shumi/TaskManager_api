package com.ne.jp.shumipro_api.mapper

import com.ne.jp.shumipro_api.dto.DailyTaskHistoryInfoDto
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

    fun findById(@Param("id") id: Int): DailyTask?

    fun insert(dailyTask: DailyTask)

    fun delete(@Param("id") id: Int): Int

    fun update(dailyTask: DailyTask): Int

    fun getDailyTaskDispOrder(@Param("username") username: String): MutableList<DailyTask>

    fun updateDailyTaskDispOrder(@Param("id") id: Int, @Param("dispOrder") dispOrder: Int)

    fun countNotDelete(@Param("username") username: String): Int
}