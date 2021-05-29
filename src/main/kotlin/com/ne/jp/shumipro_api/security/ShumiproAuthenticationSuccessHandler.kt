package com.ne.jp.shumipro_api.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.ne.jp.shumipro_api.entity.ShumiproLoginUser
import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import java.util.*
import java.util.concurrent.TimeUnit
import org.springframework.http.HttpStatus
import org.springframework.security.core.Authentication
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.springframework.security.web.WebAttributes

@Slf4j
class ShumiproAuthenticationSuccessHandler: AuthenticationSuccessHandler {
    companion object{
        private val log = LoggerFactory.getLogger(ShumiproAuthenticationSuccessHandler::class.java)
    }
    private var algorithm: Algorithm? = null

    constructor(secretKey: String?) {
        Objects.requireNonNull(secretKey, "secret key must be not null")
        algorithm = Algorithm.HMAC512(secretKey)
    }

    private val EXPIRATION_TIME = TimeUnit.MINUTES.toMillis(10L)

    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        auth: Authentication
    ) {
        if (response.isCommitted) {
            log.info("Response has already been committed.")
            return
        }
        setToken(response, generateToken(auth))
        response.status = HttpStatus.OK.value()
        clearAuthenticationAttributes(request)
    }

    private fun generateToken(auth: Authentication): String {
        val loginUser: ShumiproLoginUser = auth.principal as ShumiproLoginUser
        val issuedAt = Date()
        val notBefore = Date(issuedAt.time)
        val expiresAt = Date(issuedAt.time + EXPIRATION_TIME)
        val token: String = JWT.create()
            .withIssuedAt(issuedAt)
            .withNotBefore(notBefore)
            .withExpiresAt(expiresAt)
            .withSubject(loginUser.getUser().username)
            .sign(algorithm)
        log.debug("generate token : %s", token)
        return token
    }

    private fun setToken(response: HttpServletResponse, token: String) {
        response.setHeader("Authorization", String.format("Bearer %s", token))
        response.setHeader("Access-Control-Expose-Headers", "Authorization")
    }

    private fun clearAuthenticationAttributes(request: HttpServletRequest) {
        val session = request.getSession(false) ?: return
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION)
    }
}