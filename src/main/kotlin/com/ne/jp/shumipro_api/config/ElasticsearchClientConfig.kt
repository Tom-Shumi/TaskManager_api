package com.ne.jp.shumipro_api.config

import org.apache.http.HttpHost
import org.apache.http.client.config.RequestConfig
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder
import org.elasticsearch.client.RestClient
import org.elasticsearch.client.RestHighLevelClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component

@Component
@Configuration
class ElasticsearchClientConfig {

    @Value("\${spring.elasticsearch.host}")
    val host = ""

    @Value("\${spring.elasticsearch.port}")
    val port = 0

    @Value("\${spring.elasticsearch.https}")
    val https = false

    @Bean(name = ["restHighLevelClient"], destroyMethod = "close")
    fun client(): RestHighLevelClient {
        return getClient()
    }

    fun getClient(): RestHighLevelClient {
        return RestHighLevelClient(
            RestClient.builder(HttpHost(host, port, if (https) "https" else "http"))
                .setHttpClientConfigCallback { httpAsyncClientBuilder: HttpAsyncClientBuilder -> httpAsyncClientBuilder }
                .setRequestConfigCallback { requestConfigBuilder: RequestConfig.Builder -> requestConfigBuilder }
        )
    }

    fun getRecreateClient(): RestHighLevelClient {
        return getClient()
    }
}