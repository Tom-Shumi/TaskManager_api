package com.ne.jp.shumipro_api.response

import com.ne.jp.shumipro_api.dto.DailyTaskInfoDto
import com.ne.jp.shumipro_api.dto.DailyTaskPlainInfoDto
import com.ne.jp.shumipro_api.util.DateUtil
import java.util.*

data class DailyTaskInfoResponse(
    var id: Int,
    var username: String,
    var title: String,
    var description: String?,
    var priority: Int,
    var quota: Int,
    var deleteFlg: Int,
    var createDate: String,
    var deleteDate: String?,
    var doneDate: String?,
    var doneTime: Int?,
    var dispOrder: Int?
) {

    constructor(dto: DailyTaskInfoDto):
    this(dto.id, dto.username, dto.title, dto.description, dto.priority, dto.quota, dto.deleteFlg, DateUtil.toStringYYYYMMDD(dto.createDate),
        DateUtil.toStringYYYYMMDD(dto.deleteDate), DateUtil.toStringYYYYMMDD(dto.doneDate), dto.doneTime, dto.dispOrder)

    constructor(dto: DailyTaskPlainInfoDto):
            this(dto.id, dto.username, dto.title, dto.description, dto.priority, dto.quota, dto.deleteFlg, DateUtil.toStringYYYYMMDD(dto.createDate),
                DateUtil.toStringYYYYMMDD(dto.deleteDate), null, null, dto.dispOrder)
}