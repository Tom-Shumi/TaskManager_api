package com.ne.jp.shumipro_api.service

import com.ne.jp.shumipro_api.dto.TaskGraphDto
import com.ne.jp.shumipro_api.mapper.TaskCommentMapper
import com.ne.jp.shumipro_api.mapper.TaskMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class TaskGraphService {

    @Autowired
    lateinit var taskMapper : TaskMapper
    @Autowired
    lateinit var taskCommentMapper: TaskCommentMapper

    fun getTaskGraphInfo(username : String) : TaskGraphDto {

        val fromDate  = LocalDate.now().minusDays(6)
        val toDate = LocalDate.now().plusDays(6)

        val param = mapOf(
            "username" to username
            , "fromDate" to fromDate
            , "toDate" to toDate)

        return TaskGraphDto(taskMapper.getPlanTaskGraphInfo(param),
            taskMapper.getDoneTaskGraphIndo(param),
            taskCommentMapper.getTaskCommentGraphIndo(param))
    }
}