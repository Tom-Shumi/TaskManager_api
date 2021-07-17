package com.ne.jp.shumipro_api.entity

import java.util.*

data class DailyTask(
    var id: Int
    , var username: String
    , var title: String
    , var description: String?
    , var priority: Int
    , var quota: Int
    , var delete_flg: Int
    , var create_date: Date
) {

}