package com.ne.jp.shumipro_api.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.ne.jp.shumipro_api.Constants.Companion.ES_INDEX_NAME_THEME
import com.ne.jp.shumipro_api.dto.EsZeroSecondThinkingDocumentDto
import com.ne.jp.shumipro_api.exception.ElasticsearchException
import com.ne.jp.shumipro_api.repository.ElasticsearchClientRepository
import org.elasticsearch.action.search.SearchRequest
import org.elasticsearch.index.query.BoolQueryBuilder
import org.elasticsearch.index.query.TermQueryBuilder
import org.elasticsearch.search.builder.SearchSourceBuilder
import org.springframework.stereotype.Service

@Service
class EsZeroSecondThinkingService(private val elasticsearchClientRepository: ElasticsearchClientRepository,
                                  private val objectMapper: ObjectMapper) {

    fun searchZeroSecondThinkingDocument(username: String, searchString: String): List<EsZeroSecondThinkingDocumentDto> {
        val searchSourceBuilder = SearchSourceBuilder()
        val boolQueryBuilder = BoolQueryBuilder()
        boolQueryBuilder.filter(TermQueryBuilder("username", username))

        searchSourceBuilder.query(boolQueryBuilder)

        val request = SearchRequest(ES_INDEX_NAME_THEME).source(searchSourceBuilder)

        val response = elasticsearchClientRepository.search(request)
        val searchHit = response.hits.hits

        for (hit in searchHit) {
            val document = objectMapper.convertValue(hit.sourceAsMap, EsZeroSecondThinkingDocumentDto::class.java)
            println(document)
        }
        return listOf()
    }
}