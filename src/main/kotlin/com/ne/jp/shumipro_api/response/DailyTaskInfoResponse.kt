package com.ne.jp.shumipro_api.response

import com.ne.jp.shumipro_api.dto.DailyTaskInfoDto
import com.ne.jp.shumipro_api.util.DateUtil
import java.util.*

data class DailyTaskInfoResponse(
    var id: Int? = 0
    , var username: String? = null
    , var title: String? = null
    , var description: String? = null
    , var priority: Int? = null
    , var quota: Int? = null
    , var deleteFlg: Int? = null
    , var createDate: String? = null
    , var deleteDate: String? = null
    , var doneDate: String? = null
    , var doneTime: Int? = null
    , var dispOrder: Int? = null
) {

    fun setDailyTaskInfoResponse(dailyTaskInfoDto: DailyTaskInfoDto): DailyTaskInfoResponse{
        this.id = dailyTaskInfoDto.id
        this.username = dailyTaskInfoDto.username
        this.title = dailyTaskInfoDto.title
        this.description = dailyTaskInfoDto.description
        this.priority = dailyTaskInfoDto.priority
        this.quota = dailyTaskInfoDto.quota
        this.deleteFlg = dailyTaskInfoDto.deleteFlg
        this.createDate = DateUtil.toStringYYYYMMDD(dailyTaskInfoDto.createDate)
        this.deleteDate = DateUtil.toStringYYYYMMDD(dailyTaskInfoDto.deleteDate)
        this.doneDate = DateUtil.toStringYYYYMMDD(dailyTaskInfoDto.doneDate)
        this.doneTime = dailyTaskInfoDto.doneTime
        this.dispOrder = dailyTaskInfoDto.dispOrder
        return this;
    }
}