package com.ne.jp.shumipro_api.controller

import com.ne.jp.shumipro_api.entity.ShumiproLoginUser
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

    /**
     * タスクグラフ表示情報取得
     */
    @GetMapping
    fun getTaskGraphInfo(@AuthenticationPrincipal loginUser: ShumiproLoginUser): ResponseEntity<String> {
        return createReponseEntity(HttpStatus.OK, "")
    }

}