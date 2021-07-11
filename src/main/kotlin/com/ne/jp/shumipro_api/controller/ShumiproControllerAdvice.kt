package com.ne.jp.shumipro_api.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import javax.servlet.ServletException


@ControllerAdvice
class ShumiproControllerAdvice: BaseController() {
    @ExceptionHandler(ServletException::class)
    fun servletExceptionHandle(e: ServletException?): ResponseEntity<String> {
        return createResponseEntity(HttpStatus.BAD_REQUEST, "Bad request")
    }
}