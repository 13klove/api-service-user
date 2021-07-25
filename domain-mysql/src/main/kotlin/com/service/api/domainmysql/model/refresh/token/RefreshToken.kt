package com.service.api.domainmysql.model.refresh.token

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table
import javax.persistence.GenerationType.IDENTITY

@Entity
@Table(name = "refreshToken")
class RefreshToken (
    email: String,
    refreshToken: String,
    createAt: LocalDateTime = LocalDateTime.now(),
    expired: Boolean = false
) {

    @Id @GeneratedValue(strategy = IDENTITY)
    var id: Long? = null
    var email: String = email
    var refreshToken: String = refreshToken
    var createAt: LocalDateTime = createAt
    var updateAt: LocalDateTime? = null
    var expired: Boolean = expired

}