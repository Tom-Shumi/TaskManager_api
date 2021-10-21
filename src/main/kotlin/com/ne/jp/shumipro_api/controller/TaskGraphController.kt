package com.ne.jp.shumipro_api.controller

import com.ne.jp.shumipro_api.entity.ShumiproLoginUser
import com.ne.jp.shumipro_api.response.TaskGraphResponse
import com.ne.jp.shumipro_api.service.TaskGraphService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * タスクグラフコントローラー
 */
@RestController
@RequestMapping("/api/task/graph")
class TaskGraphController: BaseController() {

    @Autowired
    lateinit var taskGraphService : TaskGraphService

    /**
     * タスクグラフ表示情報取得
     */
    @GetMapping
    fun getTaskGraphInfo(@AuthenticationPrincipal loginUser: ShumiproLoginUser): ResponseEntity<String> {
        val taskGraphDto = taskGraphService.getTaskGraphInfo(loginUser.username)
        val taskGraphResponse = gson.toJson(TaskGraphResponse(taskGraphDto))
        return createResponseEntity(HttpStatus.OK, taskGraphResponse)
    }

}