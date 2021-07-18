package com.service.api.message.token

data class TokenResMessage(
    val accessToken: String,
    val refreshToken: String?
)
