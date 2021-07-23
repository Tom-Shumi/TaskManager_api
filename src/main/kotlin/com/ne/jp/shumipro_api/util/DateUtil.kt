package com.ne.jp.shumipro_api.util

import org.springframework.util.StringUtils
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class DateUtil {
    companion object{

        val YYYYMMDD: String = "yyyy/MM/dd"
        private val dfYYYYMMDD: SimpleDateFormat = SimpleDateFormat(YYYYMMDD)
        private val dfYYYYMMDDHHMMSS = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")

        fun toLocalDateYYYYMMDD(date: String?): LocalDate?
            = if (StringUtil.isNotEmpty(date)) LocalDate.parse(date, DateTimeFormatter.ofPattern(YYYYMMDD)) else null

        fun toStringYYYYMMDD(date: Date?):String
            = if (Objects.nonNull(date)) dfYYYYMMDD.format(date) else ""

        fun toStringYYYYMMDDHHMMSS(date: Date?):String
                = if (Objects.nonNull(date)) dfYYYYMMDDHHMMSS.format(date) else ""
    }
}