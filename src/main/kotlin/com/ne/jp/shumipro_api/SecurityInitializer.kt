package com.ne.jp.shumipro_api

import com.ne.jp.shumipro_api.config.SessionConfig
import com.ne.jp.shumipro_api.config.WebSecurityConfig
import org.springframework.security.access.SecurityConfig
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer

class SecurityInitializer :
    AbstractSecurityWebApplicationInitializer(WebSecurityConfig::class.java, SessionConfig::class.java) {
}