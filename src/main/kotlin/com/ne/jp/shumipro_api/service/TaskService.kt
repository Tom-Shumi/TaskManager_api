package com.ne.jp.shumipro_api.service

import com.ne.jp.shumipro_api.dto.TaskDto
import com.ne.jp.shumipro_api.entity.Task
import com.ne.jp.shumipro_api.mapper.TaskMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class TaskService {

    @Autowired
    lateinit var taskMapper: TaskMapper

    /**
     * タスク一覧取得
     */
    fun getTaskList(username: String): List<TaskDto>? {
        val taskList: List<Task>? = taskMapper.getTaskByUsername(username)
        if (taskList is List<Task> && taskList.size > 0){
            return taskList.map{ it -> TaskDto().setTaskDto(it)}.toList()
        } else {
            return null
        }
    }
}