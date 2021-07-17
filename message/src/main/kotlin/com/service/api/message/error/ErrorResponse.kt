package com.service.api.message.error

data class ErrorResponse(
    val message: String,
    val status: String,
    val code: String
)
