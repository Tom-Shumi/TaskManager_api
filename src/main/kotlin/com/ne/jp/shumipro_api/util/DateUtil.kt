package com.ne.jp.shumipro_api.util

import java.text.SimpleDateFormat
import java.util.*

class DateUtil {
    companion object{
        private val dfYYYYMMDD: SimpleDateFormat = SimpleDateFormat("yyyy/MM/dd")

        fun toStringYYYYMMDD(date: Date?):String
            = if (Objects.nonNull(date)) dfYYYYMMDD.format(date) else ""
    }
}