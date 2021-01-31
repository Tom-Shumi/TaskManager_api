package com.ne.jp.shumipro_api.dto

import com.ne.jp.shumipro_api.entity.User
import com.ne.jp.shumipro_api.request.UserRequest

data class UserDto(
    var username: String = ""
    ,var password: String = ""
    ,var encodedPassword: String = ""
    ,var enabledflg: Int = 0
    ,var adminflg: Int = 0) {

    fun setUserDto(userRequest: UserRequest): UserDto{
        username = userRequest.username
        password = userRequest.password
        enabledflg = userRequest.enabledflg
        adminflg = userRequest.adminflg
        return this
    }

    fun setUserDto(user: User): UserDto{
        username = user.username
        password = user.password
        enabledflg = user.enabledflg
        adminflg = user.adminflg
        return this
    }
}