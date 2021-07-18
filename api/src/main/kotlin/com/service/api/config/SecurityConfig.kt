package com.service.api.config

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy.STATELESS

@Configuration
@EnableWebSecurity
class SecurityConfig: WebSecurityConfigurerAdapter() {

    override fun configure(web: WebSecurity) {
        web.ignoring()
            .antMatchers("/swagger*/**")
            .antMatchers("/webjars/**")
            .antMatchers("/configuration/**")
    }

    override fun configure(http: HttpSecurity) {
        http
            .csrf().disable()

            .sessionManagement()
            .sessionCreationPolicy(STATELESS)

            .and()
            .authorizeRequests()
            .antMatchers("/users/**", "/v2/api-docs").permitAll()
            .anyRequest().authenticated()
    }

}