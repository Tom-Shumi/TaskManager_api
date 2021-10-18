package com.ne.jp.shumipro_api.service

import com.ne.jp.shumipro_api.dto.DailyTaskDto
import com.ne.jp.shumipro_api.dto.DailyTaskInfoDto
import com.ne.jp.shumipro_api.dto.TaskDto
import com.ne.jp.shumipro_api.entity.DailyTask
import com.ne.jp.shumipro_api.entity.Task
import com.ne.jp.shumipro_api.entity.User
import com.ne.jp.shumipro_api.mapper.DailyTaskMapper
import com.ne.jp.shumipro_api.mapper.UserMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.util.*

@Service
@Transactional
class DailyTaskService {

    @Autowired
    lateinit var dailyTaskMapper: DailyTaskMapper
    @Autowired
    lateinit var userMapper: UserMapper

    fun getDailyTaskList(username: String, includeDeleteFlg: Boolean): List<DailyTaskInfoDto> {
        return dailyTaskMapper.getDailyTaskInfoByUsername(username, LocalDate.now(), includeDeleteFlg)
    }

    fun registerDailyTask(dto: DailyTaskDto): DailyTaskDto? {
        val userCheck: User? = userMapper.getUser(dto.username)
        return if (userCheck is User){
            val dailyTask = DailyTask(dto)
            if (dailyTask.delete_flg == 1) {
                dailyTask.delete_date = LocalDate.now()
            }
            dailyTaskMapper.insert(dailyTask)
            dto.id = dailyTask.id
            dto
        } else {
            null
        }
    }

    fun updateDailyTask(dto: DailyTaskDto): DailyTaskDto? {
        val dailyTaskBefore = dailyTaskMapper.findById(dto.id!!)
        return if (dailyTaskBefore is DailyTask && dailyTaskBefore.username == dto.username) {
            val dailyTask = DailyTask(dto)

            // 削除から復活させた場合
            if (dailyTaskBefore.delete_flg == 1 && dailyTask.delete_flg == 0) {
                dailyTask.delete_date = null
            // 削除に変更した場合
            } else if (dailyTaskBefore.delete_flg == 0 && dailyTask.delete_flg == 1) {
                dailyTask.delete_date = LocalDate.now()
            }

            dailyTaskMapper.update(dailyTask)
            dto
        } else {
            null
        }
    }

    fun deleteDailyTask(id: Int, username: String): Int {
        val dailyTaskCheck = dailyTaskMapper.findById(id)
        return if (dailyTaskCheck is DailyTask && dailyTaskCheck.username == username) {
            dailyTaskMapper.delete(id)
        } else {
            0
        }
    }

    fun updateDailyTaskDispOrder(id: Int, newDispOrder: Int, username: String) {
        val dailyTaskList = dailyTaskMapper.getDailyTaskDispOrder(username)
        val prevDispOrder = dailyTaskMapper.findById(id)?.disp_order

        if (prevDispOrder is Int) {
            // 移動させるタスク取得
            val targetDailyTask: DailyTask = dailyTaskList[prevDispOrder - 1]
            // 移動前のタスクを削除
            dailyTaskList.removeAt(prevDispOrder - 1)

            dailyTaskList.add(newDispOrder - 1, targetDailyTask)

            var renumberingDispOrder = 1
            for (dailyTask in dailyTaskList )  {
                dailyTaskMapper.updateDailyTaskDispOrder(dailyTask.id!!, renumberingDispOrder)
                renumberingDispOrder++
            }
        }

    }
}