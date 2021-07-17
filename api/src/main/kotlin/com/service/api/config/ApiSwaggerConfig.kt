package com.service.api.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class ApiSwaggerConfig {

    companion object {
        const val VERSION01 = "user"
    }

    @Bean
    fun swaggerApis(): Docket =
        Docket(DocumentationType.SWAGGER_2)
            .useDefaultResponseMessages(true)
            .groupName(VERSION01)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.service.api.api"))//api endpoint
            .paths(PathSelectors.ant("/users"))//api endpoint
            .build()


}