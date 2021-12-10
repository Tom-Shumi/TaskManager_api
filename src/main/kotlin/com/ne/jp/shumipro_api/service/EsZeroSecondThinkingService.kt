package com.ne.jp.shumipro_api.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.ne.jp.shumipro_api.Constants.Companion.ES_INDEX_NAME_CONTENT
import com.ne.jp.shumipro_api.Constants.Companion.ES_INDEX_NAME_THEME
import com.ne.jp.shumipro_api.dto.EsZeroSecondThinkingDocumentDto
import com.ne.jp.shumipro_api.repository.ElasticsearchClientRepository
import org.elasticsearch.action.search.SearchRequest
import org.elasticsearch.index.query.*
import org.elasticsearch.search.builder.SearchSourceBuilder
import org.elasticsearch.search.sort.FieldSortBuilder
import org.elasticsearch.search.sort.SortOrder
import org.springframework.stereotype.Service

@Service
class EsZeroSecondThinkingService(private val elasticsearchClientRepository: ElasticsearchClientRepository,
                                  private val objectMapper: ObjectMapper) {

    fun searchZeroSecondThinkingDocument(username: String, searchString: String): Map<String, List<EsZeroSecondThinkingDocumentDto>> {
        val searchSourceBuilder = SearchSourceBuilder()
        val boolQueryBuilder = BoolQueryBuilder()

        boolQueryBuilder.filter(TermQueryBuilder("username", username))
                        .filter(RegexpQueryBuilder("content", ".*%s.*".format(searchString)))

        searchSourceBuilder.query(boolQueryBuilder)
        searchSourceBuilder.sort(FieldSortBuilder("id").order(SortOrder.DESC))

        return mapOf(ES_INDEX_NAME_THEME to search(ES_INDEX_NAME_THEME, searchSourceBuilder),
            ES_INDEX_NAME_CONTENT to search(ES_INDEX_NAME_CONTENT, searchSourceBuilder))
    }

    private fun search(index: String, searchSourceBuilder: SearchSourceBuilder): List<EsZeroSecondThinkingDocumentDto> {
        val request = SearchRequest(index).source(searchSourceBuilder)

        val response = elasticsearchClientRepository.search(request)
        val searchHit = response.hits.hits

        return searchHit.map{ hit -> objectMapper.convertValue(hit.sourceAsMap, EsZeroSecondThinkingDocumentDto::class.java)}.toList()
    }
}