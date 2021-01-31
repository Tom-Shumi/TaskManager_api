package com.ne.jp.shumipro_api.controller

import com.ne.jp.shumipro_api.dto.UserDto
import com.ne.jp.shumipro_api.request.UserRequest
import com.ne.jp.shumipro_api.response.UserResponse
import com.ne.jp.shumipro_api.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.Errors
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/user")
class UserController: BaseController() {

    @Autowired
    lateinit var userService: UserService

    /**
     * ユーザ取得
     * @param username
     * @return response
     */
    @GetMapping("/{username}")
    fun getUserInfo(@PathVariable("username") username: String): ResponseEntity<String>{
        val userDto: UserDto? = userService.getUser(username)
        if (userDto is UserDto){
            // ユーザ取得成功
            val jsonString = gson.toJson(UserResponse().setUserResponse(userDto))
            return createReponseEntity(HttpStatus.OK, jsonString)
        } else {
            // ユーザが存在しない場合
            return createReponseEntity(HttpStatus.NOT_FOUND, "$username do not found")
        }
    }

    /**
     * ユーザ登録
     * @param userRequest
     * @param errors
     * @return response
     */
    @PostMapping()
    fun registerUser(@Validated @RequestBody userRequest: UserRequest, errors: Errors) : ResponseEntity<String>{
        val errorEmg: String? = checkErrors(errors)
        if (errorEmg is String){
            // リクエストが不正だった場合
            return createReponseEntity(HttpStatus.BAD_REQUEST, errorEmg)
        } else {
            val userDtoRequest = UserDto().setUserDto(userRequest)
            val userDto: UserDto? = userService.registerUser(userDtoRequest)
            if (userDto is UserDto){
                // ユーザ登録成功
                val jsonString = gson.toJson(UserResponse().setUserResponse(userDto))
                return createReponseEntity(HttpStatus.OK, jsonString)
            } else {
                // 同一のユーザ名が既に存在した場合
                return createReponseEntity(HttpStatus.BAD_REQUEST, "${userRequest.username} already exists")
            }
        }
    }
}