package com.service.api.message.user

import io.swagger.annotations.ApiParam
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class UserUpdateReqMessage(
    @ApiParam(name = "password", defaultValue = "qwer1234!@")
    @field: NotBlank(message = "required password")
    @field: Size(min = 8, max = 12, message = "password must size 8 ~ 12")
    val password: String,
    @ApiParam(name = "new password", defaultValue = "qwer1234!@")
    @field: NotBlank(message = "required new password")
    @field: Size(min = 8, max = 12, message = "password must size 8 ~ 12")
    val newPassword: String

)
