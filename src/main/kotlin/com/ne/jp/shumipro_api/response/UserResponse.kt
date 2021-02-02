package com.ne.jp.shumipro_api.response

import com.ne.jp.shumipro_api.dto.UserDto
import com.ne.jp.shumipro_api.request.UserRequest

data class UserResponse(
    var username: String? = ""
    , var enabledflg: Int? = 0
    , var adminflg: Int? = 0
    ) {

    fun setUserResponse(userDto: UserDto): UserResponse{
        username = userDto.username
        enabledflg = userDto.enabledflg
        adminflg = userDto.adminflg
        return this
    }
}