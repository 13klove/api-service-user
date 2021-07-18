package com.service.api.api.user

import com.service.api.annotaion.HeaderValid
import com.service.api.api.user.UserController.Companion.USER_BASE_URL
import com.service.api.domainmysql.service.user.UserService
import com.service.api.message.token.TokenResMessage
import com.service.api.message.user.LoginReqMessage
import com.service.api.message.user.UserInfoResMessage
import com.service.api.message.user.UserRegReqMessage
import com.service.api.message.user.UserRegResMessage
import com.service.api.message.user.UserUpdateReqMessage
import com.service.api.message.user.UserUpdateResMessage
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
        const val X_HEADER_USER_MAIL = "x-header-user-mail"
    }

    @PostMapping
    fun regUser(@RequestBody @Validated userRegReqMessage: UserRegReqMessage): UserRegResMessage
    = userService.regUser(userRegReqMessage)

    @PostMapping("/login")
    fun login(@RequestBody @Validated loginReqMessage: LoginReqMessage): TokenResMessage
    = userService.login(loginReqMessage)

    @PutMapping
    @HeaderValid(X_HEADER_USER_MAIL)
    fun updateUser(
        @RequestHeader(X_HEADER_USER_MAIL) mail: String,
        @RequestBody userUpdateReqMessage: UserUpdateReqMessage
    ): UserUpdateResMessage
    = userService.updateUser(mail, userUpdateReqMessage.password, userUpdateReqMessage.newPassword)

    @GetMapping("/{mail}")
    @HeaderValid(X_HEADER_USER_MAIL)
    fun getUserInfo(
        @RequestHeader(X_HEADER_USER_MAIL) headerMail: String,
        @PathVariable("mail") mail: String
    ): UserInfoResMessage
    = userService.getUserInfo(headerMail, mail)

    @GetMapping("/refreshToken")
    @HeaderValid(X_HEADER_USER_MAIL)
    fun getAccessToken(@RequestHeader(X_HEADER_USER_MAIL) headerMail: String)
    = userService.getAccessToken(headerMail)

}