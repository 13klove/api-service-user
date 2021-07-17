package com.service.api.message.user

import io.swagger.annotations.ApiParam
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class UserRegReqMessage(
    @ApiParam(name = "email", defaultValue = "xxx@xxx.xxx")
    @field: NotBlank(message = "required email")
    @field: Email(message = "not mail pattern")
    var email: String,
    @ApiParam(name = "password", defaultValue = "qwer1234!@")
    @field: NotBlank(message = "required password")
    @field: Size(min = 8, max = 12, message = "password must size 8 ~ 12")
    var password: String
)
