package com.ne.jp.shumipro_api.resolver

import com.ne.jp.shumipro_api.dto.LearningInfoDto
import com.ne.jp.shumipro_api.service.LearningService
import com.ne.jp.shumipro_api.util.RequestUtil
import graphql.kickstart.tools.GraphQLMutationResolver
import org.springframework.stereotype.Component

@Component
class LearningMutationResolver(private val learningService: LearningService): GraphQLMutationResolver {

    fun registerLearning(content: String, categoryId: Int, referenceUrl: String?): LearningInfoDto {
        return learningService.registerLearning(RequestUtil.getUsername(), content, categoryId, referenceUrl)
    }
}