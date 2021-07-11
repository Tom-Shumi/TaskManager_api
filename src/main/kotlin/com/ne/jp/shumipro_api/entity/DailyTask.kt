package com.ne.jp.shumipro_api.entity

import java.util.*

data class DailyTask(
    var id: Int? = 0
    , var username: String? = null
    , var title: String? = null
    , var description: String? = null
    , var priority: Int? = null
    , var quota: Int? = null
    , var delete_flg: Int? = null
    , var create_date: Date? = null
) {

}