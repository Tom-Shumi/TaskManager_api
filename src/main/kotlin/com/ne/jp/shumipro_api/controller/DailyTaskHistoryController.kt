package com.ne.jp.shumipro_api.controller

import com.ne.jp.shumipro_api.dto.DailyTaskHistoryDto
import com.ne.jp.shumipro_api.dto.DailyTaskInfoDto
import com.ne.jp.shumipro_api.dto.TaskDto
import com.ne.jp.shumipro_api.entity.ShumiproLoginUser
import com.ne.jp.shumipro_api.request.DailyTaskHistoryRequest
import com.ne.jp.shumipro_api.request.TaskRequest
import com.ne.jp.shumipro_api.response.DailyTaskHistoryResponse
import com.ne.jp.shumipro_api.response.DailyTaskInfoResponse
import com.ne.jp.shumipro_api.response.TaskResponse
import com.ne.jp.shumipro_api.service.DailyTaskHistoryService
import com.ne.jp.shumipro_api.service.DailyTaskService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.util.CollectionUtils
import org.springframework.validation.Errors
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


/**
 * デイリータスク履歴コントローラ
 */
@RestController
@RequestMapping("/api/daily_task_history")
class DailyTaskHistoryController: BaseController()  {

    @Autowired
    lateinit var dailyTaskHistoryService: DailyTaskHistoryService

    /**
     * デイリータスク履歴登録
     */
    @PostMapping
    fun registerDailyTaskHistory(@Validated @RequestBody request: DailyTaskHistoryRequest, errors: Errors, @AuthenticationPrincipal loginUser: ShumiproLoginUser): ResponseEntity<String> {
        val errorMsg: String? = checkErrors(errors)
        if (errorMsg is String){
            // リクエストが不正だった場合
            return createResponseEntity(HttpStatus.BAD_REQUEST, errorMsg)
        }
        val dtoRequest = DailyTaskHistoryDto(request)
        val dto = dailyTaskHistoryService.registerDailyTaskHistory(dtoRequest)
        if (dto is DailyTaskHistoryDto) {
            val jsonString = gson.toJson(DailyTaskHistoryResponse(dto))
            return createResponseEntity(HttpStatus.OK, jsonString)
        } else {
            // リクエストが不正だった場合
            return createResponseEntity(HttpStatus.BAD_REQUEST, "DailyTask does not exist")
        }
    }
}