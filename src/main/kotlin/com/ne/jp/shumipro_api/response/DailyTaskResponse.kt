package com.ne.jp.shumipro_api.response

import com.ne.jp.shumipro_api.dto.DailyTaskDto
import java.time.LocalDate

data class DailyTaskResponse (
    var id: Int
    , var username: String
    , var title: String
    , var description: String?
    , var priority: Int
    , var quota: Int
    , var deleteFlg: Int
    , var createDate: LocalDate
    , var deleteDate: LocalDate?
    , var dispOrder: Int?
) {

    constructor(dto: DailyTaskDto)
            : this(dto.id!!, dto.username, dto.title, dto.description, dto.priority,
                    dto.quota, dto.deleteFlg, dto.createDate, dto.deleteDate, dto.dispOrder)
}