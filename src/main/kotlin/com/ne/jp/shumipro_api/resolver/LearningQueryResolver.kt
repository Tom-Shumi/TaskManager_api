package com.ne.jp.shumipro_api.resolver

import com.ne.jp.shumipro_api.dto.LearningInfoDto
import com.ne.jp.shumipro_api.service.LearningService
import com.ne.jp.shumipro_api.util.RequestUtil
import graphql.kickstart.tools.GraphQLQueryResolver
import org.springframework.stereotype.Component


@Component
class LearningQueryResolver(private val learningService: LearningService): GraphQLQueryResolver {
    
    fun listLearningInfo(search: String?, categoryId: Int?, nextKey: Int?): List<LearningInfoDto> {
        return learningService.listLearningInfo(RequestUtil.getUsername(),
                                                if (search.equals("")) null else search,
                                                if (categoryId == 0) null else categoryId,
                                                nextKey)
    }
}