package com.ne.jp.shumipro_api.service

import com.ne.jp.shumipro_api.dto.UserDto
import com.ne.jp.shumipro_api.entity.User
import com.ne.jp.shumipro_api.mapper.UserMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UserService {

    @Autowired
    lateinit var userMapper: UserMapper

    @Autowired
    lateinit var passwordEncoder: PasswordEncoder

    /**
     * ユーザ取得
     * @param username
     */
    fun getUser(username: String): UserDto?{
        val user: User? = userMapper.getUser(username)
        return if (user is User ) UserDto().setUserDto(user) else null
    }

    /**
     * ユーザ登録
     * @param userDto
     * @return result
     */
    fun registerUser(userDto: UserDto): UserDto? {
        val userCheck: User? = userMapper.getUser(userDto.username!!)
        if (userCheck is User){
            return null
        }
        userDto.encodedPassword = passwordEncoder.encode(userDto.password)
        val user = User().setUser(userDto)
        userMapper.insertUser(user)
        return userDto
    }
}