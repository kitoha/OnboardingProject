package com.onboarding.practice.authority.provider

import io.jsonwebtoken.Claims
import org.springframework.security.core.Authentication;
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import io.jsonwebtoken.security.Keys
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import java.util.Date

const val EXPIRATION_MILLISECONDS : Long = 1000* 60 * 60 // One Hour
@Component
class JwtTokenProvider {

    @Value("\${jwt.secret}")
    lateinit var secretKey: String

    private val key by lazy { Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey))}

    fun createToken(authentication: Authentication): String{
        val authorities: String = authentication
            .authorities
            .joinToString ( ",",transform  = GrantedAuthority::getAuthority)

        val now = Date()
        val expirationTime = Date(now.time+ EXPIRATION_MILLISECONDS)

        val accessToken = Jwts
            .builder()
            .setSubject(authentication.name)
            .claim("auth",authorities)
            .setIssuedAt(now)
            .setExpiration(expirationTime)
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()

        return accessToken
    }

    fun getAuthentication(token: String): Authentication{
        val claims: Claims = getClaims(token)

        val auth = claims["auth"]?: throw RuntimeException("잘못된 토큰입니다.")

        val authorities: Collection<GrantedAuthority> = (auth as String)
            .split(",")
            .map { SimpleGrantedAuthority(it) }

        val principal: UserDetails = User(claims.subject,"",authorities)

        return UsernamePasswordAuthenticationToken(principal, "",authorities)
    }

    private fun getClaims(token:String):Claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).body

}