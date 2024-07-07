package com.example.dcs.config

import com.example.dcs.service.CustomUserDetailsService
import com.example.dcs.uitil.JwtAuthenticationEntryPoint
import com.example.dcs.uitil.TokenRequestFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@Configuration
@EnableWebSecurity
class AppSecurityConfig(
    private val customUserDetailsService: CustomUserDetailsService,
    private val tokenRequestFilter: TokenRequestFilter,
    private val jwtAuthenticationEntryPoint: JwtAuthenticationEntryPoint
) {

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun authenticationProvider(): AuthenticationProvider? {
        val provider = DaoAuthenticationProvider()
        provider.setUserDetailsService(customUserDetailsService)
        provider.setPasswordEncoder(passwordEncoder())
        return provider
    }

    @Bean
    fun authenticationManager(): AuthenticationManager? {
        return ProviderManager(authenticationProvider())
    }

    @Bean
    @Throws(Exception::class)
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain  {
        http
            .authorizeHttpRequests { auth -> auth
                .requestMatchers("/login/**").permitAll()
                .anyRequest().authenticated()
            }
            .csrf { csrf -> csrf.disable() }
            .exceptionHandling { exceptions -> exceptions.authenticationEntryPoint(jwtAuthenticationEntryPoint) }
            .sessionManagement { session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .httpBasic(Customizer.withDefaults())
            .addFilterBefore(tokenRequestFilter, UsernamePasswordAuthenticationFilter::class.java)
        return http.build()
    }
}