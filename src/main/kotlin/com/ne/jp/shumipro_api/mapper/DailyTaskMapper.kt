package com.ne.jp.shumipro_api.mapper

import com.ne.jp.shumipro_api.dto.DailyTaskInfoDto
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import java.time.LocalDate
import java.util.*

@Mapper
interface DailyTaskMapper {

    fun getDailyTaskInfoByUsername(@Param("username") username: String, @Param("doneDate") doneDate: LocalDate): List<DailyTaskInfoDto>
}