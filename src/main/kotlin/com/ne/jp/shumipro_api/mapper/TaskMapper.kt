package com.ne.jp.shumipro_api.mapper

import com.ne.jp.shumipro_api.entity.Task
import org.apache.ibatis.annotations.Mapper
import java.time.LocalDate

@Mapper
interface TaskMapper {
    /**
     * タスク取得（ユーザ名）
     */
    fun getTaskByUsername(param: Map<String, Any?>): List<Task>?

    /**
     * タスク取得（ID）
     */
    fun getTaskById(id: Int): Task?

    /**
     * タスク登録
     */
    fun insertTask(task: Task)

    /**
     * タスク更新
     */
    fun updateTask(task: Task): Int

    /**
     * タスク削除
     */
    fun deleteTask(id: Int): Int


    fun getPlanTaskGraphInfo(param: Map<String, Any?>): List<Map<String, Int>>

    fun getDoneTaskGraphIndo(param: Map<String, Any?>): List<Map<String, Int>>
}