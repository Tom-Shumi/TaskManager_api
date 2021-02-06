package com.ne.jp.shumipro_api.service

import com.ne.jp.shumipro_api.entity.ShumiproLoginUser
import com.ne.jp.shumipro_api.entity.User
import com.ne.jp.shumipro_api.mapper.UserMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service("shumiproUserDetailsService")
class ShumiproUserDetailsService: UserDetailsService {

    @Autowired
    lateinit var userMapper: UserMapper

    override fun loadUserByUsername(username: String): UserDetails {
        val user: User? = userMapper.getUser(username)
        if (user is User){
            return ShumiproLoginUser(user)
        } else {
            throw UsernameNotFoundException("user not found")
        }
    }
}