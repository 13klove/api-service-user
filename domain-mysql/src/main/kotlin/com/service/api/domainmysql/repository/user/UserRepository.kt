package com.service.api.domainmysql.repository.user

import com.service.api.domainmysql.model.user.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, String> {

    fun findByEmail(email: String): User?

}