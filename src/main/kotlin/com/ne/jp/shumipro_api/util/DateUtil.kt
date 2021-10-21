package com.ne.jp.shumipro_api.util

import org.springframework.util.StringUtils
import java.lang.Exception
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class DateUtil {
    companion object{

        private const val YYYYMMDD: String = "yyyy-MM-dd"
        private const val nonDelimiterYYYYMMDD: String = "yyyyMMdd"

        fun toLocalDateYYYYMMDD(date: String?): LocalDate? {
            return try {
                if (StringUtil.isNotEmpty(date)) LocalDate.parse(date, DateTimeFormatter.ofPattern(YYYYMMDD)) else null
            } catch (e: Exception) {
                null
            }
        }

        fun toLocalDateNonDelimiterYYYYMMDD(date: String?): LocalDate? {
            return try {
                if (StringUtil.isNotEmpty(date)) LocalDate.parse(date, DateTimeFormatter.ofPattern(nonDelimiterYYYYMMDD)) else null
            } catch (e: Exception) {
                null
            }
        }

        fun toStringYYYYMMDD(date: LocalDate?): String
            = if (date is LocalDate) date.format(DateTimeFormatter.ofPattern(YYYYMMDD)) else ""

    }
}