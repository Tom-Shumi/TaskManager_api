package com.ne.jp.shumipro_api.security

import org.slf4j.LoggerFactory
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.http.HttpStatus
import org.springframework.security.core.AuthenticationException
import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class ShumiproAuthenticationFailureHandler: AuthenticationFailureHandler {
    companion object{
        private val log = LoggerFactory.getLogger(ShumiproAuthenticationFailureHandler::class.java)
    }

    @Throws(IOException::class, ServletException::class)
    override fun onAuthenticationFailure(
        request: HttpServletRequest,
        response: HttpServletResponse,
        exception: AuthenticationException
    ) {
        if (response.isCommitted) {
            log.info("Response has already been committed.")
            return
        }
        response.sendError(HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.reasonPhrase)
    }
}