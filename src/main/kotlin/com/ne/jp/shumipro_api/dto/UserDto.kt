package com.ne.jp.shumipro_api.dto

import com.ne.jp.shumipro_api.entity.User
import com.ne.jp.shumipro_api.request.UserRequest

data class UserDto(
    var username: String,
    var password: String?,
    var encodedPassword: String?,
    var enabledflg: Int,
    var adminflg: Int) {

    constructor(request: UserRequest): this(request.username, request.password, null, request.enabledflg, request.adminflg)

    constructor(user: User): this(user.username, user.password, null, user.enabledflg, user.adminflg)
}