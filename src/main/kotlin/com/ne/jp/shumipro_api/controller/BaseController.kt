package com.ne.jp.shumipro_api.controller

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.validation.Errors
import java.util.stream.Collectors

open class BaseController {
    companion object{
        val gson = createGson()

        private fun createGson(): Gson{
            val builder = GsonBuilder();
            builder.serializeNulls();
            return builder.create();
        }
    }

    fun createResponseEntity(status: HttpStatus, json: String?): ResponseEntity<String>
        = ResponseEntity.status(status).contentType(MediaType.APPLICATION_JSON).body(json)

    fun checkErrors(errors: Errors): String?
        = if (errors.hasErrors()){
            errors.allErrors.stream().map {it.defaultMessage}.collect(Collectors.joining(","))
        } else {
            null
        }
}