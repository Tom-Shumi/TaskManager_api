package com.ne.jp.shumipro_api.entity

import com.ne.jp.shumipro_api.dto.DailyTaskDto
import java.time.LocalDate

data class DailyTask(
    var id: Int?
    , var username: String
    , var title: String
    , var description: String?
    , var priority: Int
    , var quota: Int
    , var delete_flg: Int
    , var create_date: LocalDate
) {

    constructor(dto: DailyTaskDto)
            : this(dto.id, dto.username, dto.title, dto.description, dto.priority,
                    dto.quota, dto.deleteFlg, dto.createDate)
}