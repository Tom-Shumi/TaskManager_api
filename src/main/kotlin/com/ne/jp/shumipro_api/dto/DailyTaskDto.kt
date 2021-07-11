package com.ne.jp.shumipro_api.dto

import java.util.*

data class DailyTaskDto(
    var id: Int? = 0
    , var username: String? = null
    , var title: String? = null
    , var description: String? = null
    , var priority: Int? = null
    , var quota: Int? = null
    , var deleteFlg: Int? = null
    , var createDate: Date? = null
) {

}