package com.service.api.domainmysql.model.user

import com.service.api.domainmysql.model.user.User.UserRole.USER
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*
import javax.persistence.EnumType.STRING
import javax.persistence.GenerationType.IDENTITY

@Entity
@Table(name = "user")
class User(
    email: String,
    pwd: String
): UserDetails {

    @Id @GeneratedValue(strategy = IDENTITY)
    var id: Long? = null
    @Column(unique = true)
    var email: String? = email
    var pwd: String? = pwd
    @Enumerated(STRING)
    var role: UserRole = USER
    var deleteYn: Boolean? = false

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = mutableListOf(SimpleGrantedAuthority(role.toString()))

    override fun getPassword(): String? = pwd

    override fun getUsername(): String? = email

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean {
        return deleteYn == false
    }

    enum class UserRole{
        USER, ADMIN
    }

}