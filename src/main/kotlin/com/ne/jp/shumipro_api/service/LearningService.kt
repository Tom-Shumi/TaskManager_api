package com.ne.jp.shumipro_api.service

import com.ne.jp.shumipro_api.dto.LearningInfoDto
import com.ne.jp.shumipro_api.mapper.LearningMapper
import com.ne.jp.shumipro_api.repository.ElasticsearchClientRepository
import org.springframework.stereotype.Service

@Service
class LearningService(private val learningMapper: LearningMapper) {

    fun getLearningByUsername(username: String): List<LearningInfoDto> {
        return learningMapper.getLearningByUsername(username)
    }
}