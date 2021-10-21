package com.ne.jp.shumipro_api.entity

import com.ne.jp.shumipro_api.dto.UserDto
import java.io.Serializable

data class User(
    var username: String,
    var password: String?,
    var enabledflg: Int,
    var adminflg: Int
): Serializable{

    constructor(dto: UserDto): this(dto.username, dto.encodedPassword, dto.enabledflg, dto.adminflg)
}