package com.ne.jp.shumipro_api.controller

import com.ne.jp.shumipro_api.entity.User
import com.ne.jp.shumipro_api.response.UserResponse
import com.ne.jp.shumipro_api.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/user")
class UserController: BaseController() {

    @Autowired
    lateinit var userService: UserService

    @GetMapping("/{username}")
    fun getUserInfo(@PathVariable("username") username: String): ResponseEntity<String>{
        val user: User? = userService.getUser(username)
        if (user is User){
            val userResponse = UserResponse(
                username = user.username
                , enabledflg = user.enabledflg
                , adminflg = user.adminflg
            )
            val jsonString = gson.toJson(userResponse)
            return createReponseEntity(HttpStatus.OK, jsonString)
        } else {
            return createReponseEntity(HttpStatus.NOT_FOUND, "$username do not found")
        }
    }
}