package com.ne.jp.shumipro_api.service

import com.ne.jp.shumipro_api.dto.TaskCommentDto
import com.ne.jp.shumipro_api.mapper.TaskCommentMapper
import com.ne.jp.shumipro_api.mapper.TaskMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class TaskCommentService {

    @Autowired
    lateinit var taskCommentMapper: TaskCommentMapper

    @Value("\${data.limit}")
    var limit: Int = 0

    fun getTaskCommentList(taskId: Int, nextKey: String?): List<TaskCommentDto>{
        val param = mapOf(
            "taskId" to taskId
            , "nextKey" to nextKey
            , "limit" to limit)
        val taskCommentList = taskCommentMapper.getTaskCommentByTaskId(param)
    }
}