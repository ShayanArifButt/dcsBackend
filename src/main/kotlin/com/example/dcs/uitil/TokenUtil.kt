package com.example.dcs.uitil

import com.example.dcs.config.JwtConfig
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.time.Duration
import java.time.Instant
import java.util.*
import javax.crypto.SecretKey



@Component
class TokenUtil(private val jwtConfig: JwtConfig) {

    private val SECRET = jwtConfig.secret
    private val VALIDITY: Long = Duration.ofMinutes(jwtConfig.validityMinutes).toMillis()


    fun generateToken(userDetails: UserDetails): String {
        val claims: MutableMap<String, String?> = HashMap()
        claims["iss"] = "https://github.com/ShayanArifButt"
        return Jwts.builder()
            .claims(claims)
            .subject(userDetails.username)
            .issuedAt(Date.from(Instant.now()))
            .expiration(Date.from(Instant.now().plusMillis(VALIDITY)))
            .signWith(generateKey())
            .compact()
    }

    private fun generateKey(): SecretKey {
        val decodedKey = Base64.getDecoder().decode(SECRET)
        return Keys.hmacShaKeyFor(decodedKey)
    }

    fun extractUsername(jwt: String): String {
        val claims = getClaims(jwt)
        return claims.subject
    }

    private fun getClaims(jwt: String): Claims {
        return Jwts.parser()
            .verifyWith(generateKey())
            .build()
            .parseSignedClaims(jwt)
            .payload
    }

    fun isTokenValid(jwt: String): Boolean {
        val claims = getClaims(jwt)
        return claims.expiration.after(Date.from(Instant.now()))
    }

}