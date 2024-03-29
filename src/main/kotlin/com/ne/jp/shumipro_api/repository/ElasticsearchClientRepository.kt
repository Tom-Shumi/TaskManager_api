//package com.ne.jp.shumipro_api.repository
//
//import com.ne.jp.shumipro_api.config.ElasticsearchClientConfig
//import com.ne.jp.shumipro_api.exception.ElasticsearchException
//import org.elasticsearch.action.search.SearchRequest
//import org.elasticsearch.action.search.SearchResponse
//import org.elasticsearch.client.RequestOptions
//import org.elasticsearch.client.RestHighLevelClient
//import org.springframework.stereotype.Repository
//import java.lang.Exception
//
//@Repository
//class ElasticsearchClientRepository(
//    private val elasticsearchClientConfig: ElasticsearchClientConfig,
//    private var restHighLevelClient: RestHighLevelClient,
//) {
//
//    fun setClient(restHighLevelClient: RestHighLevelClient) {
//        this.restHighLevelClient = restHighLevelClient
//    }
//
//    fun search(request: SearchRequest): SearchResponse {
//        return try {
//            restHighLevelClient.search(request, RequestOptions.DEFAULT)
//        } catch (e: Exception) {
//            setClient(elasticsearchClientConfig.getRecreateClient())
//            throw e
//        }
//    }
//
//}