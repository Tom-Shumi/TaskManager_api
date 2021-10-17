package com.ne.jp.shumipro_api.controller

import com.ne.jp.shumipro_api.dto.DailyTaskDto
import com.ne.jp.shumipro_api.dto.DailyTaskInfoDto
import com.ne.jp.shumipro_api.entity.ShumiproLoginUser
import com.ne.jp.shumipro_api.request.DailyTaskRequest
import com.ne.jp.shumipro_api.response.DailyTaskInfoResponse
import com.ne.jp.shumipro_api.response.DailyTaskResponse
import com.ne.jp.shumipro_api.service.DailyTaskService
import com.ne.jp.shumipro_api.util.DateUtil.Companion.toLocalDateYYYYMMDD
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.util.CollectionUtils
import org.springframework.validation.Errors
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

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
    fun getDailyTaskList(@AuthenticationPrincipal loginUser: ShumiproLoginUser, @RequestParam("includeDeleteFlg") includeDeleteFlg: Boolean): ResponseEntity<String> {
        val dailyTaskInfoDtoList: List<DailyTaskInfoDto> = dailyTaskService.getDailyTaskList(loginUser.username, includeDeleteFlg)
        return if (CollectionUtils.isEmpty(dailyTaskInfoDtoList)){
            createResponseEntity(HttpStatus.NO_CONTENT, null)
        } else {
            // デイリータスク取得成功
            val jsonString = gson.toJson(dailyTaskInfoDtoList.map{DailyTaskInfoResponse().setDailyTaskInfoResponse(it)}.toList())
            createResponseEntity(HttpStatus.OK, jsonString)
        }
    }

    /**
     * デイリータスク登録
     */
    @PostMapping
    fun registerDailyTask(@Validated @RequestBody request: DailyTaskRequest, errors: Errors,
                        @AuthenticationPrincipal loginUser: ShumiproLoginUser): ResponseEntity<String> {
        val errorMsg: String? = checkErrors(errors)
        if (errorMsg is String){
            // リクエストが不正だった場合
            return createResponseEntity(HttpStatus.BAD_REQUEST, errorMsg)
        }
        val dtoRequest = DailyTaskDto(request, loginUser.username, LocalDate.now())

        val dto: DailyTaskDto? = dailyTaskService.registerDailyTask(dtoRequest)

        return if (dto is DailyTaskDto){
            // デイリータスク登録成功
            val jsonString = gson.toJson(DailyTaskResponse(dto))
            createResponseEntity(HttpStatus.OK, jsonString)
        } else {
            // ユーザが存在しない場合
            createResponseEntity(HttpStatus.BAD_REQUEST, "${loginUser.username} does not exist")
        }
    }

    /**
     * デイリータスク更新
     */
    @PutMapping("/{id}")
    fun updateDailyTask(@PathVariable("id") id: Int,@Validated @RequestBody request: DailyTaskRequest,
                   errors: Errors, @AuthenticationPrincipal loginUser: ShumiproLoginUser): ResponseEntity<String> {
        val errorMsg: String? = checkErrors(errors)
        if (errorMsg is String){
            // リクエストが不正だった場合
            return createResponseEntity(HttpStatus.BAD_REQUEST, errorMsg)
        }
        val dtoRequest = DailyTaskDto(request, loginUser.username,
            toLocalDateYYYYMMDD(request.createDate)!!, toLocalDateYYYYMMDD(request.deleteDate))
        dtoRequest.id = id
        val dto: DailyTaskDto? = dailyTaskService.updateDailyTask(dtoRequest)
        return if (dto is DailyTaskDto){
            // デイリータスク更新成功
            val jsonString = gson.toJson(DailyTaskResponse(dto))
            createResponseEntity(HttpStatus.OK, jsonString)
        } else {
            // タスクが存在しない場合
            createResponseEntity(HttpStatus.BAD_REQUEST, "this daily task does not exist")
        }
    }

    /**
     * デイリータスク削除
     */
    @DeleteMapping("/{id}")
    fun deleteDailyTask(@PathVariable("id") id: Int, @AuthenticationPrincipal loginUser: ShumiproLoginUser) : ResponseEntity<String> {

        if (dailyTaskService.deleteDailyTask(id, loginUser.username) > 0){
            // 削除成功
            return createResponseEntity(HttpStatus.NO_CONTENT, "")
        } else {
            // デイリータスクが存在しない場合
            return createResponseEntity(HttpStatus.NOT_FOUND, "this daily task does not found")
        }
    }

    /**
     * デイリータスク表示順更新
     */
    @PutMapping("/dispOrder")
    fun updateDailyTaskDispOrder(@RequestParam(name = "fromDispOrder") fromDispOrder: Int, @RequestParam(name = "toDispOrder") toDispOrder: Int,
                        @AuthenticationPrincipal loginUser: ShumiproLoginUser): ResponseEntity<String> {

        dailyTaskService.updateDailyTaskDispOrder(fromDispOrder, toDispOrder, loginUser.username)
        return createResponseEntity(HttpStatus.NO_CONTENT, "")
    }
}