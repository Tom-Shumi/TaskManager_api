package com.ne.jp.shumipro_api.resolver

import com.ne.jp.shumipro_api.entity.Book
import graphql.kickstart.tools.GraphQLQueryResolver
import org.springframework.stereotype.Component

@Component
class BookResolver: GraphQLQueryResolver {
    fun bookById(id: String): Book {
        return Book(
            id = id,
            name = "test"
        )
    }
}