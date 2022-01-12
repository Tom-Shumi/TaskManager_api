package com.ne.jp.shumipro_api.service

import com.ne.jp.shumipro_api.dto.LearningInfoDto
import com.ne.jp.shumipro_api.entity.Learning
import com.ne.jp.shumipro_api.mapper.LearningMapper
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.function.Consumer

@Service
class LearningService(private val learningMapper: LearningMapper) {

    @Value("\${data.limit}")
    var limit: Int = 0

    fun listLearningInfo(username: String, search: String?, categoryId: Int?, nextKey: Int?): List<LearningInfoDto> {
        return learningMapper.listLearningInfo(username, search, categoryId, nextKey, limit)
    }

    @Transactional
    fun registerLearning(username: String, content: String, categoryId: Int, referenceUrl: String?): LearningInfoDto {
        return execute(null, username, content, categoryId, referenceUrl, learningMapper::register)
    }

    @Transactional
    fun updateLearning(id: Int, username: String, content: String, categoryId: Int, referenceUrl: String?): LearningInfoDto {
        return execute(id, username, content, categoryId, referenceUrl, learningMapper::update)
    }

    fun deleteLearning(id: Int, username: String): Int {
        return learningMapper.delete(id, username)
    }

    private fun execute(id: Int?, username: String, content: String, categoryId: Int, referenceUrl: String?, func: Consumer<Learning>): LearningInfoDto {
        val learning = Learning(id, username, categoryId, content, referenceUrl, null)
        func.accept(learning)
        return learningMapper.getById(learning.id!!)
    }
}