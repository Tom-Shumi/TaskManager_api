package com.ne.jp.shumipro_api.resolver

import com.ne.jp.shumipro_api.entity.LearningCategory
import com.ne.jp.shumipro_api.service.LearningCategoryService
import com.ne.jp.shumipro_api.util.RequestUtil
import graphql.kickstart.tools.GraphQLMutationResolver
import org.springframework.stereotype.Component

@Component
class LearningCategoryMutationResolver(private val learningCategoryService: LearningCategoryService): GraphQLMutationResolver {

    fun registerLearningCategory(name: String): LearningCategory {
        return learningCategoryService.registerLearningCategory(RequestUtil.getUsername(), name)
    }

    fun updateLearningCategory(id: Int, name: String): LearningCategory {
        return learningCategoryService.updateLearningCategory(id, RequestUtil.getUsername(), name)
    }

    fun deleteLearningCategory(id: Int): Int {
        return learningCategoryService.deleteLearningCategory(id, RequestUtil.getUsername())
    }

    fun bulkRegisterLearningCategory(learningCategoryList: List<LearningCategory>): Int {
        return learningCategoryService.bulkRegisterLearningCategory(learningCategoryList, RequestUtil.getUsername())
    }
}