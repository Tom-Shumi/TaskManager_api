package com.ne.jp.shumipro_api.mapper

import com.ne.jp.shumipro_api.entity.Task
import org.apache.ibatis.annotations.Mapper

@Mapper
interface TaskMapper {
    /**
     * タスク取得
     */
    fun getTaskByUsername(username: String): List<Task>?

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
}