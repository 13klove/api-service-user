package com.service.api.domainmysql.repository.refresh.token

import com.service.api.domainmysql.model.refresh.token.RefreshToken
import org.springframework.data.jpa.repository.JpaRepository

interface RefreshTokenRepository: JpaRepository<RefreshToken, Long> {

    fun findByEmailAndExpiredFalse(email: String): RefreshToken?

}