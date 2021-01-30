package com.ne.jp.shumipro_api.service

import com.ne.jp.shumipro_api.entity.User
import com.ne.jp.shumipro_api.mapper.UserMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UserService {

    @Autowired
    lateinit var userMapper: UserMapper

    /**
     * ユーザ取得
     */
    fun getUser(username: String): User? = userMapper.getUser(username)
}