package com.ne.jp.shumipro_api.service

import com.ne.jp.shumipro_api.dto.LearningInfoDto
import com.ne.jp.shumipro_api.entity.LearningCategory
import com.ne.jp.shumipro_api.mapper.LearningCategoryMapper
import com.ne.jp.shumipro_api.mapper.LearningMapper
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class LearningCategoryService(private val learningCategoryMapper: LearningCategoryMapper) {

    fun listLearningCategory(username: String): List<LearningCategory> {
        return learningCategoryMapper.getByUsername(username)
    }
}