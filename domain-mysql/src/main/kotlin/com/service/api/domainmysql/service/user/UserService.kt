package com.service.api.domainmysql.service.user

import com.service.api.domainmysql.exception.user.AlreadyUserEmailException
import com.service.api.domainmysql.model.user.User
import com.service.api.domainmysql.repository.user.UserRepository
import com.service.api.message.user.UserRegReqMessage
import com.service.api.message.user.UserRegResMessage
import mu.KLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class UserService(
    private val userRepository: UserRepository
) {

    companion object: KLogging() {
        const val USER_ALREADY_MESSAGE = "already user email"
    }

    @Transactional
    fun regUser(userRegReqMessage: UserRegReqMessage): UserRegResMessage {
        userRepository.findByEmail(userRegReqMessage.email)?. run {
            logger.info { "already user email: ${this.email}" }
            throw AlreadyUserEmailException(USER_ALREADY_MESSAGE)
        }

        val user = userRepository.save(
            User(userRegReqMessage.email, userRegReqMessage.password)
        )

        logger.info { "reg user: ${user.email}" }

        return UserRegResMessage(user.email!!)
    }

}