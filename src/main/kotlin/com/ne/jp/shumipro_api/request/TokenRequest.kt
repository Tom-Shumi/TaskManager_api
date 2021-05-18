package com.ne.jp.shumipro_api.request

import java.io.Serializable
import javax.validation.constraints.NotNull

class TokenRequest: Serializable {

    companion object {
        private const val serialVersionUID: Long = 0
    }

    @field:NotNull(message="{e.001}")
    lateinit var username: String

    @field:NotNull(message="{e.001}")
    lateinit var password: String
}