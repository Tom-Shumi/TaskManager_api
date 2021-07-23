package com.ne.jp.shumipro_api.util

import java.util.*
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest

class StringUtil {

    companion object{

        fun isEmpty(str: String?):Boolean {
            if (Objects.isNull(str) || str.equals("")) {
                return true
            }
            return false
        }

        fun isNotEmpty(str: String?): Boolean {
            return !isEmpty(str)
        }
    }
}