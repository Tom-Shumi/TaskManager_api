package com.ne.jp.shumipro_api.entity

import com.ne.jp.shumipro_api.dto.UserDto
import java.io.Serializable

data class User(
    var username: String? = ""
    , var password: String? = ""
    , var enabledflg: Int? = 0
    , var adminflg: Int? = 0
): Serializable{


    fun setUser(userDto: UserDto): User{
        username = userDto.username
        password = userDto.encodedPassword
        enabledflg = userDto.enabledflg
        adminflg = userDto.adminflg
        return this
    }
}