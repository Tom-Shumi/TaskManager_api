package com.ne.jp.shumipro_api.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.ne.jp.shumipro_api.dto.SessionBean
import com.ne.jp.shumipro_api.entity.ShumiproLoginUser
import com.ne.jp.shumipro_api.entity.User
import com.ne.jp.shumipro_api.mapper.UserMapper
import com.ne.jp.shumipro_api.request.TokenRequest
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors
import javax.servlet.http.HttpServletRequest
import org.springframework.web.util.ContentCachingRequestWrapper




@Service
class ShumiproUserDetailsService: AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {

    val logger: Logger = LoggerFactory.getLogger(ShumiproUserDetailsService::class.java)

    @Autowired
    lateinit var tokenService: TokenService
    @Autowired
    lateinit var sessionBean: SessionBean

    val objectMapper: ObjectMapper = ObjectMapper()

    override fun loadUserDetails(token: PreAuthenticatedAuthenticationToken): UserDetails {

        logger.info("exec: loadUserDetails")
        logger.info("session:" + sessionBean.user)

        if (Objects.nonNull(sessionBean.user)) {
            return ShumiproLoginUser(sessionBean.user!!)
        }

        val request: HttpServletRequest = token.credentials as HttpServletRequest;

        val requestWrapper = ContentCachingRequestWrapper(request)

        logger.info("request:" + request.requestURL)

        val tokenRequest: TokenRequest? = objectMapper.readValue(requestWrapper.reader.lines().collect(Collectors.joining("")), TokenRequest::class.java)

        if (tokenRequest is TokenRequest) {
            val user = tokenService.authentication(tokenRequest.username, tokenRequest.password)
            if (user is User) {
                sessionBean.user = user

                logger.info("auth: OK")
                return ShumiproLoginUser(user)
            }
        }

        throw UsernameNotFoundException("Authentication failure")
    }
}