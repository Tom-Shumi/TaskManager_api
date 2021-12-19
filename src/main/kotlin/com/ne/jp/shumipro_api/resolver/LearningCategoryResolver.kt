package com.ne.jp.shumipro_api.resolver

import com.ne.jp.shumipro_api.entity.LearningCategory
import com.ne.jp.shumipro_api.service.LearningCategoryService
import com.ne.jp.shumipro_api.util.RequestUtil
import graphql.kickstart.tools.GraphQLQueryResolver
import org.springframework.stereotype.Component

@Component
class LearningCategoryResolver(private val learningCategoryService: LearningCategoryService): GraphQLQueryResolver {

    fun listLearningCategory(): List<LearningCategory> {
        return learningCategoryService.listLearningCategory(RequestUtil.getUsername())
    }
}