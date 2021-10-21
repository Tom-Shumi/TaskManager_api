package com.ne.jp.shumipro_api.service

import com.ne.jp.shumipro_api.dto.TaskDto
import com.ne.jp.shumipro_api.entity.Task
import com.ne.jp.shumipro_api.entity.User
import com.ne.jp.shumipro_api.mapper.TaskMapper
import com.ne.jp.shumipro_api.mapper.UserMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.util.*

@Service
@Transactional
class TaskService {

    @Autowired
    lateinit var taskMapper: TaskMapper
    @Autowired
    lateinit var userMapper: UserMapper

    /**
     * タスク一覧取得
     */
    fun getTaskList(username: String, status: String?): List<TaskDto>? {
        val param = mapOf(
            "username" to username
            , "status" to status)
        val taskList: List<Task>? = taskMapper.getTaskByUsername(param)
        return if (taskList is List<Task> && taskList.isNotEmpty()){
            taskList.map{TaskDto(it)}.toList()
        } else {
            null
        }
    }

    /**
     * タスク登録
     */
    fun registerTask(taskDto: TaskDto): TaskDto? {
        val userCheck: User? = userMapper.getUser(taskDto.username!!)
        return if (userCheck is User){
            val task = Task(taskDto)
            taskMapper.insertTask(task)
            taskDto.id = task.id
            taskDto
        } else {
            null
        }
    }

    /**
     * タスク更新
     */
    fun updateTask(taskDto: TaskDto): TaskDto? {
        val taskBefore = taskMapper.getTaskById(taskDto.id!!)
        return if (taskBefore is Task && taskBefore.username.equals(taskDto.username)) {
            val task = Task(taskDto)
            if (task.status == 3) {
                task.planDate = taskBefore.planDate
                task.doneDate = if (task.doneDate is LocalDate) {task.doneDate} else { LocalDate.now()}
            } else {
                task.doneDate = taskBefore.doneDate
            }
            taskMapper.updateTask(task)
            taskDto
        } else {
            null
        }
    }

    /**
     * タスクステータス更新
     */
    fun updateStatusTask(id: Int, status: Int): TaskDto? {
        val task = taskMapper.getTaskById(id)
        return if (task is Task) {
            task.status = status
            this.updateTask(TaskDto(task))
        } else {
            null
        }
    }

    /**
     * タスク削除
     */
    fun deleteTask(id: Int, username: String): Int{
        val taskCheck = taskMapper.getTaskById(id)
        return if (taskCheck is Task && taskCheck.username.equals(username)) {
            taskMapper.deleteTask(id)
        } else {
            0
        }
    }
}