package com.service.api.domainmysql.model.user

import com.service.api.domainmysql.model.user.User.UserRole.USER
import javax.persistence.*
import javax.persistence.EnumType.STRING
import javax.persistence.GenerationType.IDENTITY

@Entity
@Table(name = "user")
class User(
    email: String,
    pwd: String
) {

    @Id @GeneratedValue(strategy = IDENTITY)
    var id: Long? = null
    @Column(unique = true)
    var email: String? = email
    var pwd: String? = pwd
    @Enumerated(STRING)
    var role: UserRole = USER
    var deleteYn: Boolean? = false

    enum class UserRole{
        USER, ADMIN
    }

}