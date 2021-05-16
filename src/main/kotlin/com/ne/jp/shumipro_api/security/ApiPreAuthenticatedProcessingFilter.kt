package com.ne.jp.shumipro_api.security

import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter
import java.util.*
import javax.servlet.http.HttpServletRequest

class ApiPreAuthenticatedProcessingFilter: AbstractPreAuthenticatedProcessingFilter() {

    override fun getPreAuthenticatedPrincipal(request: HttpServletRequest?): Any {
        return ""
    }

    override fun getPreAuthenticatedCredentials(request: HttpServletRequest?): Any {
        return Optional.ofNullable(request).orElse(null)
    }
}