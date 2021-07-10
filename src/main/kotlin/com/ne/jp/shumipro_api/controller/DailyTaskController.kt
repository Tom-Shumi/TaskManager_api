package com.ne.jp.shumipro_api.controller

import com.ne.jp.shumipro_api.entity.ShumiproLoginUser
import com.ne.jp.shumipro_api.service.DailyTaskService
import com.ne.jp.shumipro_api.service.TaskService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
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
        return createReponseEntity(HttpStatus.NO_CONTENT, null)
    }
}