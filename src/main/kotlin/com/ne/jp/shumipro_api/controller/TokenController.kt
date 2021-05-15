package com.ne.jp.shumipro_api.controller

import com.ne.jp.shumipro_api.mapper.UserMapper
import com.ne.jp.shumipro_api.request.TaskRequest
import com.ne.jp.shumipro_api.service.TokenService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.web.csrf.DefaultCsrfToken
import org.springframework.validation.Errors
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/api/token")
class TokenController: BaseController() {

    @Autowired
    lateinit var tokenService: TokenService

    @PostMapping
    fun getToken(@Validated @RequestBody taskRequest: TaskRequest, errors: Errors): ResponseEntity<String> {
        val errorMsg: String? = checkErrors(errors)
        if (errorMsg is String){
            // リクエストが不正だった場合
            return createReponseEntity(HttpStatus.BAD_REQUEST, errorMsg)
        }
        return createReponseEntity(HttpStatus.OK, null)
    }
}