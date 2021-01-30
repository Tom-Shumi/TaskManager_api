package com.ne.jp.shumipro_api.controller

import com.google.gson.Gson
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity

open class BaseController {
    open val gson = Gson()

    fun createReponseEntity(status: HttpStatus, json: String): ResponseEntity<String>
        = ResponseEntity.status(status).contentType(MediaType.APPLICATION_JSON).body(json)
}