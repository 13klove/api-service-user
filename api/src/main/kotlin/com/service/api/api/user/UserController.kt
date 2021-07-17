package com.service.api.api.user

import com.service.api.api.user.UserController.Companion.USER_BASE_URL
import com.service.api.domainmysql.service.user.UserService
import com.service.api.message.user.UserRegReqMessage
import com.service.api.message.user.UserRegResMessage
import mu.KLogging
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(USER_BASE_URL)
class UserController(
    private val userService: UserService
) {

    companion object: KLogging() {
        const val USER_BASE_URL = "users"
    }

    @PostMapping
    fun regUser(@RequestBody @Validated userRegReqMessage: UserRegReqMessage): UserRegResMessage
    = userService.regUser(userRegReqMessage)

}