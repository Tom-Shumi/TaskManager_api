package com.ne.jp.shumipro_api.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.ne.jp.shumipro_api.Constants.Companion.ES_INDEX_NAME_CONTENT
import com.ne.jp.shumipro_api.Constants.Companion.ES_INDEX_NAME_THEME
import com.ne.jp.shumipro_api.dto.EsZeroSecondThinkingDocumentDto
import com.ne.jp.shumipro_api.repository.ElasticsearchClientRepository
import org.elasticsearch.action.search.SearchRequest
import org.elasticsearch.index.query.BoolQueryBuilder
import org.elasticsearch.index.query.MatchQueryBuilder
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
                        .filter(MatchQueryBuilder("content", searchString))

        searchSourceBuilder.query(boolQueryBuilder)

        val themeList = search(ES_INDEX_NAME_THEME, searchSourceBuilder)
        val contentList = search(ES_INDEX_NAME_CONTENT, searchSourceBuilder)

        // TODO 返却するための処理
        return listOf()
    }

    private fun search(index: String, searchSourceBuilder: SearchSourceBuilder): List<EsZeroSecondThinkingDocumentDto> {
        val request = SearchRequest(index).source(searchSourceBuilder)

        val response = elasticsearchClientRepository.search(request)
        val searchHit = response.hits.hits

        return searchHit.map{ hit -> objectMapper.convertValue(hit.sourceAsMap, EsZeroSecondThinkingDocumentDto::class.java)}.toList()
    }
}