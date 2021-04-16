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
            taskComment.createDate = Date()
            taskCommentMapper.insertTaskComment(taskComment)
            taskCommentDto.id = taskComment.id
            taskCommentDto
        } else {
            null
        }
    }
}