package com.service.api.domainmysql.exception.user

import java.lang.RuntimeException

class NotFoundUserException(message: String) : RuntimeException(message) {
}