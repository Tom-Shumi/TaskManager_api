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
        return if (user is User ) UserDto(user) else null
    }

    /**
     * ユーザ登録
     * @param userDto
     * @return result
     */
    fun registerUser(userDto: UserDto): UserDto? {
        val userCheck: User? = userMapper.getUser(userDto.username)
        if (userCheck is User){
            return null
        }
        userDto.encodedPassword = passwordEncoder.encode(userDto.password)
        val user = User(userDto)
        userMapper.insertUser(user)
        return userDto
    }

    /**
     * ユーザ更新
     */
    fun updateUser(userDto: UserDto): UserDto? {
        val userCheck: User? = userMapper.getUser(userDto.username)
        if (userCheck is User){
            userDto.encodedPassword = passwordEncoder.encode(userDto.password)
            val user = User(userDto)
            userMapper.updateUser(user)
            return userDto
        } else {
            return null
        }
    }

    /**
     * ユーザ削除
     */
    fun deleteUser(username: String) : Int = userMapper.deleteUser(username)
}