package com.ne.jp.shumipro_api.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import java.util.TimeZone




@Configuration
class WebMvcConfig: WebMvcConfigurer {

    @Value("\${front.origin}")
    private val frontOrigin: String = ""

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOrigins(frontOrigin)
            .allowedMethods("GET", "POST", "PUT", "DELETE")
        super.addCorsMappings(registry)
    }
}