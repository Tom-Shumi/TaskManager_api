package com.ne.jp.shumipro_api.service

import com.ne.jp.shumipro_api.entity.User
import com.ne.jp.shumipro_api.mapper.UserMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class TokenService {

    @Autowired
    lateinit var userMapper: UserMapper
    @Autowired
    lateinit var passwordEncoder: PasswordEncoder

    fun authentication(username: String, rawPassword: String): Boolean {
        val user = userMapper.getUser(username)
        return if (user is User) { passwordEncoder.matches(rawPassword, user.password) } else { false }
    }
}