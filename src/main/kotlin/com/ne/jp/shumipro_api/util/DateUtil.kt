package com.ne.jp.shumipro_api.util

import java.text.SimpleDateFormat
import java.util.*

class DateUtil {
    companion object{
        private val dfYYYYMMDD: SimpleDateFormat = SimpleDateFormat("yyyy/MM/dd")
        private val dfYYYYMMDDHHMMSS = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")

        fun toStringYYYYMMDD(date: Date?):String
            = if (Objects.nonNull(date)) dfYYYYMMDD.format(date) else ""

        fun toStringYYYYMMDDHHMMSS(date: Date?):String
                = if (Objects.nonNull(date)) dfYYYYMMDDHHMMSS.format(date) else ""
    }
}