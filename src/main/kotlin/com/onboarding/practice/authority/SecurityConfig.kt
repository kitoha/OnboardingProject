package com.onboarding.practice.authority

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.autoconfigure.security.servlet.PathRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun filterChain(httpSecurity: HttpSecurity) : SecurityFilterChain{
        httpSecurity.csrf { obj: CsrfConfigurer<HttpSecurity> -> obj.disable() }
            .sessionManagement{it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)}
            .authorizeHttpRequests{ it.anyRequest().permitAll()}

        return httpSecurity.build()
    }

    @Bean
    fun passwordEncode(): PasswordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder()

    @Bean
    @ConditionalOnProperty(name = ["spring.h2.console.enabled"], havingValue = "true")
    fun configureH2ConsoleEnable(): WebSecurityCustomizer {
        return WebSecurityCustomizer { web: WebSecurity ->
            web.ignoring()
                .requestMatchers(PathRequest.toH2Console())
        }
    }
}