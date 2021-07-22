package com.ne.jp.shumipro_api.service

import com.ne.jp.shumipro_api.dto.DailyTaskDto
import com.ne.jp.shumipro_api.dto.DailyTaskInfoDto
import com.ne.jp.shumipro_api.entity.DailyTask
import com.ne.jp.shumipro_api.entity.Task
import com.ne.jp.shumipro_api.entity.User
import com.ne.jp.shumipro_api.mapper.DailyTaskMapper
import com.ne.jp.shumipro_api.mapper.UserMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
@Transactional
class DailyTaskService {

    @Autowired
    lateinit var dailyTaskMapper: DailyTaskMapper
    @Autowired
    lateinit var userMapper: UserMapper

    fun getDailyTaskList(username: String): List<DailyTaskInfoDto> {
        return dailyTaskMapper.getDailyTaskInfoByUsername(username, LocalDate.now())
    }

    fun registerDailyTask(dto: DailyTaskDto): DailyTaskDto? {
        val userCheck: User? = userMapper.getUser(dto.username)
        return if (userCheck is User){
            val dailyTask = DailyTask(dto)
            dailyTaskMapper.insert(dailyTask)
            dto.id = dailyTask.id
            dto
        } else {
            null
        }
    }
}