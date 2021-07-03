package com.ne.jp.shumipro_api.dto

import com.ne.jp.shumipro_api.entity.User
import org.springframework.stereotype.Component
import org.springframework.web.context.annotation.SessionScope
import java.io.Serializable

@Component
@SessionScope
class SessionBean: Serializable {
    companion object {
        private const val serialVersionUID: Long = 0
    }
    var user: User? = null
}