package com.ne.jp.shumipro_api.service

import com.ne.jp.shumipro_api.entity.ShumiproLoginUser
import com.ne.jp.shumipro_api.mapper.UserMapper
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.*

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