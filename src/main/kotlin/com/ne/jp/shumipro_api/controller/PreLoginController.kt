package com.ne.jp.shumipro_api.controller

import org.springframework.security.web.csrf.DefaultCsrfToken
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("prelogin")
class PreLoginController {

    @GetMapping
    fun preLogin(request: HttpServletRequest): String {
        val token: DefaultCsrfToken? = request.getAttribute("_csrf") as DefaultCsrfToken
        if (token is DefaultCsrfToken){
            return token.token
        } else {
            throw RuntimeException("could not get a token.")
        }
    }
}