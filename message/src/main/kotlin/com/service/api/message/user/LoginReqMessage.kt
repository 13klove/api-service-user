package com.service.api.message.user

import javax.validation.constraints.NotBlank

data class LoginReqMessage(
    @field:NotBlank(message = "required mail")
    val mail: String,
    @field:NotBlank(message = "required password")
    val password: String
)
