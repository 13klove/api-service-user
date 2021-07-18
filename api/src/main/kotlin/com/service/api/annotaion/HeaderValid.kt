package com.service.api.annotaion

import kotlin.annotation.AnnotationTarget.FUNCTION

@Target(FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class HeaderValid(
    vararg val headers: String
)


