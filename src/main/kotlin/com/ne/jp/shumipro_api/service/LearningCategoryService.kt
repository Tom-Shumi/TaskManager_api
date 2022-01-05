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
        var registerCount = 0

        learningCategoryMapper.deleteByUsername(username)

        if (learningCategoryList.isNotEmpty()) {
            learningCategoryList.forEach { l -> l.username = username }
            registerCount = learningCategoryMapper.bulkRegister(learningCategoryList)
        }

        // TODO カテゴリ一利用チェック

        return registerCount
    }

    private fun execute(id: Int?, username: String, name: String, func: Consumer<LearningCategory>): LearningCategory {
        val learningCategory = LearningCategory(id, username, name)
        func.accept(learningCategory)
        return learningCategoryMapper.getById(learningCategory.id!!)
    }
}