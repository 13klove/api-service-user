package com.service.api.domainmysql.exception.user

import java.lang.RuntimeException

class AlreadyUserEmailException(message: String) : RuntimeException(message) {
}