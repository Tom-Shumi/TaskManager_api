package com.ne.jp.shumipro_api.request

data class UserRequest(
    val username: String
    , val password: String
    , val enabledflg: Int
    , val adminflg: Int
) {
}