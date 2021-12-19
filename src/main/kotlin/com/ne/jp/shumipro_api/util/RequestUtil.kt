package com.ne.jp.shumipro_api.util

import org.springframework.security.core.context.SecurityContextHolder
import java.text.SimpleDateFormat
import java.util.*
import javax.servlet.ServletRequest
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest

class RequestUtil {

    companion object{

        fun getCookie(request: HttpServletRequest, key: String?):String? {
            val cookie: Array<Cookie>? = request.cookies

            if (cookie is Array<Cookie>) {
                for (i in cookie.indices) {
                    if (cookie[i].name.equals(key)) {
                        return cookie[i].value
                    }
                }
            }
            return null
        }

        fun getUsername():String {
            return SecurityContextHolder.getContext().authentication.name
        }
    }
}