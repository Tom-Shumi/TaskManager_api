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
            taskList.map{ it -> TaskDto().setTaskDto(it)}.toList()
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
            val task = Task().setTask(taskDto)
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
            val task = Task().setTask(taskDto)
            if (task.status == 3) {
                task.plan_date = taskBefore.plan_date
                task.done_date = if (task.done_date is Date) {task.done_date} else {Date()}
            } else {
                task.done_date = taskBefore.done_date
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
    fun updateStatusTask(taskDto: TaskDto): TaskDto? {
        val task = taskMapper.getTaskById(taskDto.id!!)
        return if (task is Task) {
            task.status = taskDto.status
            this.updateTask(TaskDto().setTaskDto(task))
        } else {
            null
        }
    }

    /**
     * タスク削除
     */
    fun deleteTask(taskDto: TaskDto): Int{
        val taskCheck = taskMapper.getTaskById(taskDto.id!!)
        return if (taskCheck is Task && taskCheck.username.equals(taskDto.username)) {
            taskMapper.deleteTask(taskDto.id!!)
        } else {
            0
        }
    }
}