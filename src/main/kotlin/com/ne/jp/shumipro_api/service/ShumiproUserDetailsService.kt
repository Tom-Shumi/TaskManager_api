package com.ne.jp.shumipro_api.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.ne.jp.shumipro_api.dto.SessionBean
import com.ne.jp.shumipro_api.entity.ShumiproLoginUser
import com.ne.jp.shumipro_api.entity.User
import com.ne.jp.shumipro_api.mapper.UserMapper
import com.ne.jp.shumipro_api.request.TokenRequest
import com.ne.jp.shumipro_api.security.ShumiproAuthenticationFailureHandler
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors
import javax.servlet.http.HttpServletRequest
import org.springframework.web.util.ContentCachingRequestWrapper

@Service
class ShumiproUserDetailsService: UserDetailsService {
    companion object{
        private val log = LoggerFactory.getLogger(ShumiproUserDetailsService::class.java)
    }

    @Autowired
    lateinit var userMapper: UserMapper

    override fun loadUserByUsername(username: String): UserDetails {

        return Optional.ofNullable(userMapper.getUser(username))
            .map { u -> ShumiproLoginUser(u) }
            .orElseThrow { UsernameNotFoundException("user not found") }

    }

}