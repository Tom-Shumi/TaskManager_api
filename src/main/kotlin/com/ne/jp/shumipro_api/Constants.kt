package com.ne.jp.shumipro_api

class Constants {

    companion object {
        const val JWT_TOKEN = "Authorization"
        const val LOGIN_PATH = "/login"
        const val CREATE_USER_PATH = "/api/user/noAuth"

        const val ZERO_SECOND_THINKING_PREFIX_CONTENT = "・"
        const val ZERO_SECOND_THINKING_PREFIX_WHY = "　　[なぜ？]"

        const val ES_INDEX_NAME_THEME = "zero_second_thinking_theme"
        const val ES_INDEX_NAME_CONTENT = "zero_second_thinking_content"
    }
}