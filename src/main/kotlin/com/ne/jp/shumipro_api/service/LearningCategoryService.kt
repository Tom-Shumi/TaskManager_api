package com.ne.jp.shumipro_api.service

import com.ne.jp.shumipro_api.entity.LearningCategory
import com.ne.jp.shumipro_api.mapper.LearningCategoryMapper
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.function.Consumer

@Service
class LearningCategoryService(private val learningCategoryMapper: LearningCategoryMapper) {

    fun listLearningCategory(username: String): List<LearningCategory> {
        return learningCategoryMapper.getByUsername(username)
    }

    @Transactional
    fun registerLearningCategory(username: String, name: String): LearningCategory {
        return execute(null, username, name, learningCategoryMapper::register)
    }

    @Transactional
    fun updateLearningCategory(id: Int, username: String, name: String): LearningCategory {
        return execute(id, username, name, learningCategoryMapper::update)
    }

    fun deleteLearningCategory(id: Int, username: String): Int {
        return learningCategoryMapper.delete(id, username)
    }

    @Transactional
    fun bulkRegisterLearningCategory(learningCategoryList: List<LearningCategory>, username: String): Int {
        learningCategoryMapper.deleteByUsername(username)

        // TODO カテゴリ一括登録
        learningCategoryList.forEach { l -> l.username = username }


        // TODO カテゴリ一利用チェック

        return 0
    }

    private fun execute(id: Int?, username: String, name: String, func: Consumer<LearningCategory>): LearningCategory {
        val learningCategory = LearningCategory(id, username, name)
        func.accept(learningCategory)
        return learningCategoryMapper.getById(learningCategory.id!!)
    }
}