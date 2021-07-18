package com.service.api.domainmysql.service.user

import com.service.api.domainmysql.exception.user.AlreadyUserEmailException
import com.service.api.domainmysql.exception.user.NotFoundUserException
import com.service.api.domainmysql.model.user.User
import com.service.api.domainmysql.repository.user.UserRepository
import com.service.api.domainmysql.service.token.TokenProvider
import com.service.api.message.token.TokenResMessage
import com.service.api.message.user.LoginReqMessage
import com.service.api.message.user.UserInfoResMessage
import com.service.api.message.user.UserRegReqMessage
import com.service.api.message.user.UserRegResMessage
import com.service.api.message.user.UserUpdateResMessage
import mu.KLogging
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val tokenProvider: TokenProvider,
) {

    companion object: KLogging() {
        const val USER_ALREADY_MESSAGE = "already user email"
        const val NOT_FOUND_USER = "not found user"
        const val NO_PERMISSION = "no permission"
    }

    @Transactional
    fun regUser(userRegReqMessage: UserRegReqMessage): UserRegResMessage {
        userRepository.findByEmail(userRegReqMessage.email)?.run {
            logger.info { "already user email: ${this.email}" }
            throw AlreadyUserEmailException(USER_ALREADY_MESSAGE)
        }

        val user = userRepository.save(
            User(
                userRegReqMessage.email,
                passwordEncoder.encode(userRegReqMessage.password)
            )
        )

        logger.info { "reg user: ${user.email}" }

        return UserRegResMessage(user.email!!)
    }

    fun login(loginReqMessage: LoginReqMessage): TokenResMessage {
        val user = userRepository.findByEmail(loginReqMessage.mail) ?: run {
            logger.info { "not found user: ${loginReqMessage.mail}" }
            throw NotFoundUserException(NOT_FOUND_USER)
        }

        if (!passwordEncoder.matches(loginReqMessage.password, user.pwd)) {
            throw NotFoundUserException(NOT_FOUND_USER)
        }

        return tokenProvider.getAllToken(user)
    }

    @Transactional
    fun updateUser(mail: String, password: String, newPassword: String): UserUpdateResMessage {
        val user = userRepository.findByEmail(mail)?.run {
            this.pwd = passwordEncoder.encode(password)
            this
        } ?: throw NotFoundUserException(NOT_FOUND_USER)

        if (!passwordEncoder.matches(newPassword, user.pwd)) {
            throw NotFoundUserException(NOT_FOUND_USER)
        }

        return UserUpdateResMessage(user.email!!)
    }

    fun getUserInfo(headerMail: String, mail: String): UserInfoResMessage {
        if (headerMail != mail) throw NotFoundUserException(NO_PERMISSION)
        val user = userRepository.findByEmail(mail) ?: throw NotFoundUserException(NOT_FOUND_USER)

        return UserInfoResMessage(user.email!!)
    }

    fun getAccessToken(headerMail: String): TokenResMessage {
        val user = userRepository.findByEmail(headerMail) ?: throw NotFoundUserException(NOT_FOUND_USER)

        return tokenProvider.getAccessToken(user)
    }

}