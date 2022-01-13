package com.ne.jp.shumipro_api.controller

import com.ne.jp.shumipro_api.entity.ShumiproLoginUser
import com.ne.jp.shumipro_api.request.ZeroSecondThinkingRequest
import com.ne.jp.shumipro_api.request.ZeroSecondThinkingUpdateRequest
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

//        if (search is String) {
//            zeroSecondThinkingList = zeroSecondThinkingService.getZeroSecondThinkingListFromEs(loginUser.username, search, nextKey)
//        } else {
//            zeroSecondThinkingList = zeroSecondThinkingService.getZeroSecondThinkingListFromDb(loginUser.username, search, nextKey)
//        }
        val zeroSecondThinkingList = zeroSecondThinkingService.getZeroSecondThinkingListFromDb(loginUser.username, search, nextKey)

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

    /**
     * 0秒思考削除処理
     */
    @DeleteMapping("/{themeId}")
    fun deleteZeroSecondThinking(@PathVariable("themeId") themeId: Int, @AuthenticationPrincipal loginUser: ShumiproLoginUser) : ResponseEntity<String> {
        return if (zeroSecondThinkingService.deleteZeroSecondThinking(themeId, loginUser.username) > 0){
            // 0秒思考削除成功
            createResponseEntity(HttpStatus.NO_CONTENT, "")
        } else {
            // 0秒思考が存在しない場合
            createResponseEntity(HttpStatus.NOT_FOUND, "this theme does not found")
        }
    }

    /**
     * 0秒思考テーマ更新処理
     */
    @PostMapping("/{themeId}")
    fun updateTheme(@PathVariable("themeId") themeId: Int, @RequestBody requestBody: ZeroSecondThinkingUpdateRequest,
                    @AuthenticationPrincipal loginUser: ShumiproLoginUser) : ResponseEntity<String> {
        return if (zeroSecondThinkingService.updateZeroSecondThinkingTheme(themeId, requestBody.updateText, loginUser.username) > 0){
            // 0秒思考テーマ更新成功
            createResponseEntity(HttpStatus.NO_CONTENT, "")
        } else {
            // 対象が存在しない場合
            createResponseEntity(HttpStatus.NOT_FOUND, "this theme does not found")
        }
    }

    /**
     * 0秒思考コンテンツ更新処理
     */
    @PostMapping("/{themeId}/{contentId}")
    fun updateContent(@PathVariable("themeId") themeId: Int, @PathVariable("contentId") contentId: Int,
                      @RequestBody requestBody: ZeroSecondThinkingUpdateRequest, @AuthenticationPrincipal loginUser: ShumiproLoginUser) : ResponseEntity<String> {
        return if (zeroSecondThinkingService.updateZeroSecondThinkingContent(themeId, contentId, requestBody.updateText, requestBody.isWhyText, loginUser.username) > 0){
            // 0秒思考コンテンツ更新成功
            createResponseEntity(HttpStatus.NO_CONTENT, "")
        } else {
            // 対象が存在しない場合
            createResponseEntity(HttpStatus.NOT_FOUND, "this content does not found")
        }
    }
}