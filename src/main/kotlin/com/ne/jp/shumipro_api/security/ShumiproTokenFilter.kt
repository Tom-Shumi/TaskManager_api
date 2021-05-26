package com.ne.jp.shumipro_api.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTVerificationException
import com.ne.jp.shumipro_api.mapper.UserMapper
import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.web.filter.GenericFilterBean
import java.util.*
import org.springframework.http.HttpStatus

import javax.servlet.http.HttpServletResponse

import org.springframework.security.core.context.SecurityContextHolder
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken

import com.auth0.jwt.interfaces.DecodedJWT

import com.auth0.jwt.JWTVerifier
import com.ne.jp.shumipro_api.entity.ShumiproLoginUser
import com.ne.jp.shumipro_api.entity.User

import javax.servlet.http.HttpServletRequest

@Slf4j
class ShumiproTokenFilter: GenericFilterBean {
    companion object{
        private val log = LoggerFactory.getLogger(ShumiproTokenFilter::class.java)
    }

    private var userMapper: UserMapper
    private var algorithm: Algorithm

    constructor(userMapper: UserMapper, secretKey: String) {
        Objects.requireNonNull(secretKey, "secret key must be not null")
        this.userMapper = userMapper
        algorithm = Algorithm.HMAC512(secretKey)
    }

    @Throws(IOException::class, ServletException::class)
    override fun doFilter(request: ServletRequest, response: ServletResponse, filterChain: FilterChain) {
        val token: String? = resolveToken(request)
        if (token == null) {
            filterChain.doFilter(request, response)
            return
        }
        try {
            authentication(verifyToken(token))
        } catch (e: JWTVerificationException) {
            log.error("verify token error", e)
            SecurityContextHolder.clearContext()
            (response as HttpServletResponse).sendError(
                HttpStatus.UNAUTHORIZED.value(),
                HttpStatus.UNAUTHORIZED.reasonPhrase
            )
        }
        filterChain.doFilter(request, response)
    }

    private fun resolveToken(request: ServletRequest): String? {
        val token = (request as HttpServletRequest).getHeader("Authorization")
        return if (token == null || !token.startsWith("Bearer ")) {
            null
        } else {
            token.substring(7) // remove "Bearer "
        }
    }

    private fun verifyToken(token: String): DecodedJWT {
        val verifier: JWTVerifier = JWT.require(algorithm).build()
        return verifier.verify(token)
    }

    private fun authentication(jwt: DecodedJWT) {
        val username = jwt.subject
        val user = userMapper.getUser(username)
        if (user is User) {
            val shumiproLoginUser = ShumiproLoginUser(user)
            SecurityContextHolder.getContext().authentication =
                UsernamePasswordAuthenticationToken(
                    shumiproLoginUser,
                    null,
                    shumiproLoginUser.authorities
                )
        }
    }
}