package com.ne.jp.shumipro_api.dto

import com.ne.jp.shumipro_api.entity.DailyTaskHistory
import com.ne.jp.shumipro_api.request.DailyTaskRequest
import java.time.LocalDate
import java.util.*

data class DailyTaskDto(
    var id: Int?
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
    constructor(request: DailyTaskRequest, username: String, createDate: LocalDate)
            : this(null, username, request.title, request.description,
        request.priority, request.quota, request.deleteFlg, createDate, null, null)

    constructor(request: DailyTaskRequest, username: String, createDate: LocalDate, deleteDate: LocalDate?)
            : this(null, username, request.title, request.description,
        request.priority, request.quota, request.deleteFlg, createDate, deleteDate, null)
}