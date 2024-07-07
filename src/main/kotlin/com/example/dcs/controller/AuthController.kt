package com.example.dcs.controller

import com.example.dcs.uitil.TokenUtil
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/login")
class AuthController(
    private val authenticationManager: AuthenticationManager,
    private val userDetailsService: UserDetailsService,
    private val tokenUtil: TokenUtil
) {

    @PostMapping
    fun createAuthenticationToken(@RequestBody authRequest: AuthRequest): ResponseEntity<*> {
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(authRequest.username, authRequest.password)
        )

        val userDetails: UserDetails = userDetailsService.loadUserByUsername(authRequest.username)
        val jwt: String = tokenUtil.generateToken(userDetails)

        return ResponseEntity.ok(mapOf("token" to jwt))
    }
}

data class AuthRequest(
    val username: String,
    val password: String
)