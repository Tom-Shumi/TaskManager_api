package com.ne.jp.shumipro_api.controller

import com.ne.jp.shumipro_api.dto.ZeroSecondThinkingDto
import com.ne.jp.shumipro_api.entity.ShumiproLoginUser
import com.ne.jp.shumipro_api.request.TaskCommentRequest
import com.ne.jp.shumipro_api.request.ZeroSecondThinkingRequest
import com.ne.jp.shumipro_api.response.ZeroSecondThinkingResponse
import com.ne.jp.shumipro_api.service.ZeroSecondThinkingService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.util.CollectionUtils
import org.springframework.validation.Errors
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

/**
 * 0秒思考コントローラ
 */
@RestController
@RequestMapping("/api/zeroSecondThinking")
class ZeroSecondThinkingController: BaseController() {

    @Autowired
    lateinit var zeroSecondThinkingService: ZeroSecondThinkingService

    /**
     * 0秒思考一覧取得
     */
    @GetMapping
    fun getZeroSecondThinkingList(@AuthenticationPrincipal loginUser: ShumiproLoginUser,
                                  @RequestParam(name = "search", required = false) search: String?,
                                  @RequestParam(name = "nextKey", required = false) nextKey: Int?): ResponseEntity<String> {

        val zeroSecondThinkingList = zeroSecondThinkingService.getZeroSecondThinkingList(loginUser.username, search, nextKey)

        return if (CollectionUtils.isEmpty(zeroSecondThinkingList)){
            createResponseEntity(HttpStatus.NO_CONTENT, null)
        } else {
            // タスクコメント取得成功
            val jsonString = gson.toJson(zeroSecondThinkingList.map{ ZeroSecondThinkingResponse(it) }.toList())
            createResponseEntity(HttpStatus.OK, jsonString)
        }
    }

    /**
     * 0秒思考登録処理
     */
    @PostMapping
    fun registerZeroSecondThinking(@Validated @RequestBody zeroSecondThinkingRequest: ZeroSecondThinkingRequest,
                                   errors: Errors, @AuthenticationPrincipal loginUser: ShumiproLoginUser): ResponseEntity<String> {
        val errorMsg: String? = checkErrors(errors)
        if (errorMsg is String){
            // リクエストが不正だった場合
            return createResponseEntity(HttpStatus.BAD_REQUEST, errorMsg)
        }
        val id = zeroSecondThinkingService.registerZeroSecondThinking(loginUser.username,
            zeroSecondThinkingRequest.theme, zeroSecondThinkingRequest.contentList)
        return if (id is Int){
            // 登録成功
            val param = mapOf("id" to id)
            val jsonString = gson.toJson(param)
            createResponseEntity(HttpStatus.OK, jsonString)
        } else {
            // ユーザが存在しない場合
            createResponseEntity(HttpStatus.BAD_REQUEST, "${loginUser.username} does not exist")
        }
    }
}