package com.service.api.domainmysql.service.token

import com.service.api.domainmysql.model.user.User
import com.service.api.message.token.TokenResMessage
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm.HS512
import io.jsonwebtoken.security.Keys

import mu.KLogging
import org.springframework.core.env.Environment
import org.springframework.stereotype.Component
import java.security.Key
import java.time.Instant
import java.time.LocalDateTime
import java.util.Base64
import java.util.Date

@Component
class TokenProvider(
    private val environment: Environment
) {

    companion object: KLogging() {
        const val AUTHORITIES_KEY = "auth"
        const val REFRESH_KEY = "refresh"
        const val ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 30//30분
        const val REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 7//7일
    }

    private final val key: Key

    init {
        val secreatKey = environment.getProperty("secret.key")
        key = Keys.hmacShaKeyFor(Base64.getEncoder().encode(secreatKey?.toByteArray()))
    }

    fun getAllToken(user: User): TokenResMessage {
        logger.info { "getToken user: ${user.email} | date: ${LocalDateTime.now()}" }
        val expiredDate = Instant.now().toEpochMilli()

        val accessToken = createAccessToken(user, expiredDate)
        val refreshToken = createRefreshToken(user, expiredDate)

        return TokenResMessage(accessToken, refreshToken)
    }

    fun getAccessToken(user: User): TokenResMessage {
        logger.info { "getToken user: ${user.email} | date: ${LocalDateTime.now()}" }
        val expiredDate = Instant.now().toEpochMilli()

        return TokenResMessage(
            createAccessToken(user, expiredDate),
            null
        )
    }

    private fun createAccessToken(user: User, expiredDate: Long): String
        = Jwts.builder()
        .setSubject(user.email)
        .claim(AUTHORITIES_KEY, user.role)
        .setExpiration(Date(expiredDate + ACCESS_TOKEN_EXPIRE_TIME))
        .signWith(key, HS512)
        .compact()

    private fun createRefreshToken(user: User, expiredDate: Long) = Jwts.builder()
        .setSubject(user.email)
        .claim(REFRESH_KEY, true)
        .setExpiration(Date(expiredDate + REFRESH_TOKEN_EXPIRE_TIME))
        .signWith(key, HS512)
        .compact()

}