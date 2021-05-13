package com.ne.jp.shumipro_api

import com.ne.jp.shumipro_api.config.SessionConfig
import org.springframework.security.access.SecurityConfig
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer

class SecurityInitializer :
    AbstractSecurityWebApplicationInitializer(SecurityConfig::class.java, SessionConfig::class.java) {
}