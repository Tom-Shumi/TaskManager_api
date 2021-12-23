package com.ne.jp.shumipro_api.service

import com.ne.jp.shumipro_api.dto.LearningInfoDto
import com.ne.jp.shumipro_api.entity.Learning
import com.ne.jp.shumipro_api.mapper.LearningMapper
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class LearningService(private val learningMapper: LearningMapper) {

    @Value("\${data.limit}")
    var limit: Int = 0

    fun listLearningInfo(username: String, search: String?, categoryId: Int?, nextKey: Int?): List<LearningInfoDto> {
        return learningMapper.listLearningInfo(username, search, categoryId, nextKey, limit)
    }

    @Transactional
    fun registerLearning(username: String, content: String, categoryId: Int, referenceUrl: String?): LearningInfoDto {
        val learning = Learning(null, username, categoryId, content, referenceUrl, null)
        learningMapper.register(learning)
        return learningMapper.getById(learning.id!!)
    }
}