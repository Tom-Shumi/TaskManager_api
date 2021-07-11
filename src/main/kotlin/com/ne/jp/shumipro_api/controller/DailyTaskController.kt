package com.ne.jp.shumipro_api.controller

import com.ne.jp.shumipro_api.dto.DailyTaskInfoDto
import com.ne.jp.shumipro_api.dto.TaskDto
import com.ne.jp.shumipro_api.entity.ShumiproLoginUser
import com.ne.jp.shumipro_api.response.DailyTaskInfoResponse
import com.ne.jp.shumipro_api.response.TaskCommentResponse
import com.ne.jp.shumipro_api.service.DailyTaskService
import com.ne.jp.shumipro_api.service.TaskService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.util.CollectionUtils
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * デイリータスクコントローラ
 */
@RestController
@RequestMapping("/api/daily_task")
class DailyTaskController: BaseController() {

    @Autowired
    lateinit var dailyTaskService: DailyTaskService

    /**
     * 当日のデイリータスク一覧情報取得
     */
    @GetMapping("", "/")
    fun getDailyTaskList(@AuthenticationPrincipal loginUser: ShumiproLoginUser): ResponseEntity<String> {
        val dailyTaskInfoDtoList: List<DailyTaskInfoDto> = dailyTaskService.getDailyTaskList(loginUser.username)
        return if (CollectionUtils.isEmpty(dailyTaskInfoDtoList)){
            createResponseEntity(HttpStatus.NO_CONTENT, null)
        } else {
            // タスク取得成功
            val jsonString = gson.toJson(dailyTaskInfoDtoList.map{DailyTaskInfoResponse().setDailyTaskInfoResponse(it)}.toList())
            createResponseEntity(HttpStatus.OK, jsonString)
        }
    }
}