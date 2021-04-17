package com.ne.jp.shumipro_api.service

import com.ne.jp.shumipro_api.dto.TaskCommentDto
import com.ne.jp.shumipro_api.entity.Task
import com.ne.jp.shumipro_api.entity.TaskComment
import com.ne.jp.shumipro_api.mapper.TaskCommentMapper
import com.ne.jp.shumipro_api.mapper.TaskMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
class TaskCommentService {

    @Autowired
    lateinit var taskCommentMapper: TaskCommentMapper
    @Autowired
    lateinit var taskMapper: TaskMapper

    @Value("\${data.limit}")
    var limit: Int = 0

    fun getTaskCommentList(taskId: Int, nextKey: Int?): List<TaskCommentDto>? {
        val param = mapOf(
            "taskId" to taskId
            , "nextKey" to nextKey
            , "limit" to limit)
        val taskCommentList = taskCommentMapper.getTaskCommentByTaskId(param)
        return if (taskCommentList is List<TaskComment> && taskCommentList.isNotEmpty()){
            taskCommentList.map{ it -> TaskCommentDto().setTaskCommentDto(it)}.toList()
        } else {
            null
        }
    }

    fun registerTaskComment(taskCommentDto: TaskCommentDto): TaskCommentDto?{
        val taskCheck: Task? = taskMapper.getTaskById(taskCommentDto.taskId!!)
        return if (taskCheck is Task){
            val taskComment = TaskComment().setTaskComment(taskCommentDto)
            taskComment.create_date = Date()
            taskCommentMapper.insertTaskComment(taskComment)
            taskCommentDto.id = taskComment.id
            taskCommentDto
        } else {
            null
        }
    }

    fun updateTaskComment(taskCommentDto: TaskCommentDto): TaskCommentDto?{
        val param = mapOf(
            "id" to taskCommentDto.id
            , "taskId" to taskCommentDto.taskId
            , "username" to taskCommentDto.username)
        val taskComment = taskCommentMapper.getTaskCommentByIdAndTaskIdAndUsername(param)
        return if (taskComment is TaskComment) {
            taskComment.comment = taskCommentDto.comment
            taskCommentMapper.updateTaskComment(taskComment)
            taskCommentDto.createDate = taskComment.create_date
            taskCommentDto
        } else {
            null
        }
    }

    fun deleteTaskComment(taskCommentDto: TaskCommentDto): Int{
        val param = mapOf(
            "id" to taskCommentDto.id
            , "taskId" to taskCommentDto.taskId
            , "username" to taskCommentDto.username)
        val taskComment = taskCommentMapper.getTaskCommentByIdAndTaskIdAndUsername(param)
        return if (taskComment is TaskComment) {
            taskCommentMapper.deleteTaskComment(taskCommentDto.id!!)
        } else {
            0
        }
    }
}