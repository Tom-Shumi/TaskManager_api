package com.ne.jp.shumipro_api.response

import com.ne.jp.shumipro_api.dto.UserDto
import com.ne.jp.shumipro_api.request.UserRequest

data class UserResponse(
    var username: String,
    var enabledflg: Int,
    var adminflg: Int
    ) {

    constructor(dto: UserDto): this(dto.username, dto.enabledflg, dto.adminflg)

}