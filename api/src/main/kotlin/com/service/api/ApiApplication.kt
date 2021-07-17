package com.service.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
//template에 옮겨야 할것 logging, swagger, test
@SpringBootApplication
class ApiApplication

fun main(args: Array<String>) {
    runApplication<ApiApplication>(*args)
}