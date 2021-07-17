package com.service.api.domainmysql.model.user

import org.hibernate.annotations.NotFound
import javax.persistence.*

@Entity
@Table(name = "user")
class User(
    email: String,
    password: String
) {
    @NotFound
    @Id @GeneratedValue
    var id: Long? = null
    @Column(unique = true)
    var email: String? = email
    var password: String? = password
    var deleteYn: Boolean? = false

}