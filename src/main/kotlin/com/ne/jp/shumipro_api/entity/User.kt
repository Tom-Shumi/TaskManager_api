package com.ne.jp.shumipro_api.entity

data class User(
    val username: String
    , val password: String
    , val enabledflg: Int
    , val adminflg: Int
) {
}