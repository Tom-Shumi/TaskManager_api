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

/**
 * ユーザコントローラ
 */
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
        val errorMsg: String? = checkErrors(errors)
        if (errorMsg is String){
            // リクエストが不正だった場合
            return createReponseEntity(HttpStatus.BAD_REQUEST, errorMsg)
        }
        val userDtoRequest = UserDto().setUserDto(userRequest)
        val userDto: UserDto? = userService.registerUser(userDtoRequest)
        if (userDto is UserDto){
            // ユーザ登録成功
            val jsonString = gson.toJson(UserResponse().setUserResponse(userDto))
            return createReponseEntity(HttpStatus.OK, jsonString)
        } else {
            // 同一のユーザ名が既に存在した場合
            return createReponseEntity(HttpStatus.BAD_REQUEST, "${userRequest.username} already exist")
        }
    }

    /**
     * ユーザ更新
     * @param userRequest
     * @param errors
     * @return response
     */
    @PutMapping("/{username}")
    fun updateUser(@PathVariable("username") username: String, @Validated @RequestBody userRequest: UserRequest, errors: Errors) : ResponseEntity<String>{
        val errorMsg: String? = checkErrors(errors)
        if (errorMsg is String){
            // リクエストが不正だった場合
            return createReponseEntity(HttpStatus.BAD_REQUEST, errorMsg)
        } else {
            val userDtoRequest = UserDto().setUserDto(userRequest)
            userDtoRequest.username = username
            val userDto: UserDto? = userService.updateUser(userDtoRequest)
            if (userDto is UserDto){
                // ユーザ更新成功
                val jsonString = gson.toJson(UserResponse().setUserResponse(userDto))
                return createReponseEntity(HttpStatus.OK, jsonString)
            } else {
                // 対象ユーザが存在しなかった場合
                return createReponseEntity(HttpStatus.BAD_REQUEST, "${userRequest.username} is not exist")
            }
        }
    }

    /**
     * ユーザ削除
     */
    @DeleteMapping("/{username}")
    fun deleteUser(@PathVariable("username") username: String): ResponseEntity<String>{
        val result: Int = userService.deleteUser(username)
        if (result > 0){
            // ユーザ削除成功
            return createReponseEntity(HttpStatus.NO_CONTENT, "")
        } else {
            // ユーザが存在しない場合
            return createReponseEntity(HttpStatus.NOT_FOUND, "$username does not found")
        }
    }
}