package com.ne.jp.shumipro_api.controller

import com.ne.jp.shumipro_api.dto.SessionBean
import com.ne.jp.shumipro_api.entity.User
import com.ne.jp.shumipro_api.mapper.UserMapper
import com.ne.jp.shumipro_api.request.TaskRequest
import com.ne.jp.shumipro_api.request.TokenRequest
import com.ne.jp.shumipro_api.service.TokenService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.web.csrf.DefaultCsrfToken
import org.springframework.validation.Errors
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/api/token")
class TokenController: BaseController() {

    @Autowired
    lateinit var sessionBean: SessionBean

    @PostMapping
    fun getToken(): ResponseEntity<String> {
        return if (Objects.nonNull(sessionBean.user)) {
            createReponseEntity(HttpStatus.OK, null)
        } else {
            createReponseEntity(HttpStatus.UNAUTHORIZED, null)
        }
    }
}