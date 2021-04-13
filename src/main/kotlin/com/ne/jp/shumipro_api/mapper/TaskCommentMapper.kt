package com.ne.jp.shumipro_api.mapper

import com.ne.jp.shumipro_api.entity.Task
import com.ne.jp.shumipro_api.entity.TaskComment
import org.apache.ibatis.annotations.Mapper

@Mapper
interface TaskCommentMapper {

    /**
     * タスクコメント取得（タスクID）
     */
    fun getTaskCommentByTaskId(param: Map<String, Any?>): List<TaskComment>?

}