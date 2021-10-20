package com.ne.jp.shumipro_api.controller

import com.ne.jp.shumipro_api.dto.DailyTaskHistoryDto
import com.ne.jp.shumipro_api.dto.DailyTaskHistoryInfoDto
import com.ne.jp.shumipro_api.entity.ShumiproLoginUser
import com.ne.jp.shumipro_api.request.DailyTaskHistoryRequest
import com.ne.jp.shumipro_api.response.*
import com.ne.jp.shumipro_api.service.DailyTaskHistoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.validation.Errors
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*


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
    fun registerDailyTaskHistory(@Validated @RequestBody request: DailyTaskHistoryRequest, errors: Errors,
                                 @AuthenticationPrincipal loginUser: ShumiproLoginUser): ResponseEntity<String> {
        val errorMsg: String? = checkErrors(errors)
        if (errorMsg is String){
            // リクエストが不正だった場合
            return createResponseEntity(HttpStatus.BAD_REQUEST, errorMsg)
        }
        val dtoRequest = DailyTaskHistoryDto(request)
        val dto = dailyTaskHistoryService.registerTodayDailyTaskHistory(dtoRequest)
        return if (dto is DailyTaskHistoryDto) {
            val jsonString = gson.toJson(DailyTaskHistoryResponse(dto))
            createResponseEntity(HttpStatus.OK, jsonString)
        } else {
            // リクエストが不正だった場合
            createResponseEntity(HttpStatus.BAD_REQUEST, "DailyTask does not exist")
        }
    }

    /**
     * デイリータスク履歴登録（遅れて登録）
     */
    @PostMapping("/register_later")
    fun registerDailyTaskHistoryRegisterLater(@Validated @RequestBody request: DailyTaskHistoryRequest,
                                              errors: Errors, @AuthenticationPrincipal loginUser: ShumiproLoginUser): ResponseEntity<String> {
        val errorMsg: String? = checkErrors(errors)
        if (errorMsg is String){
            // リクエストが不正だった場合
            return createResponseEntity(HttpStatus.BAD_REQUEST, errorMsg)
        }
        val dtoRequest = DailyTaskHistoryDto(request)
        val dto = dailyTaskHistoryService.registerLaterDailyTaskHistory(dtoRequest)
        return if (dto is DailyTaskHistoryDto) {
            val jsonString = gson.toJson(DailyTaskHistoryResponse(dto))
            createResponseEntity(HttpStatus.OK, jsonString)
        } else {
            // リクエストが不正だった場合
            createResponseEntity(HttpStatus.BAD_REQUEST, "DailyTask does not exist")
        }
    }

    @GetMapping
    fun getDailyTaskHistory(@AuthenticationPrincipal loginUser: ShumiproLoginUser,
                            @RequestParam(name = "nextTargetDate", required = false) nextTargetDate: String?): ResponseEntity<String> {
        val dailyTaskHistoryList = dailyTaskHistoryService.getDailyTaskHistory(loginUser.username , nextTargetDate)

        return if (dailyTaskHistoryList is List<List<DailyTaskHistoryInfoDto>>) {

            val jsonString = gson.toJson(dailyTaskHistoryList.map{ innerList -> innerList.map { DailyTaskHistoryInfoResponse(it) }.toList()}.toList())
            createResponseEntity(HttpStatus.OK, jsonString)
        } else {
            createResponseEntity(HttpStatus.BAD_REQUEST, "bad request")
        }
    }
}