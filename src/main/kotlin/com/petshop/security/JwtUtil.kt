package com.petshop.security

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component
import java.util.Date
import javax.crypto.SecretKey

@Component
class JwtUtil {

    // Chave secreta (em produção, isso viria de variável de ambiente)
    private val secretKey: SecretKey = Keys.hmacShaKeyFor(
        "minha-chave-super-secreta-com-pelo-menos-32-caracteres-petshop".toByteArray()
    )

    private val expirationMs = 86400000L // 24 horas

    // Gera token JWT a partir do email do usuário
    fun gerarToken(email: String): String {
        val agora = Date()
        val expiracao = Date(agora.time + expirationMs)

        return Jwts.builder()
            .subject(email)
            .issuedAt(agora)
            .expiration(expiracao)
            .signWith(secretKey)
            .compact()
    }

    // Extrai o email do token
    fun extrairEmail(token: String): String? {
        return try {
            Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .payload
                .subject
        } catch (e: Exception) {
            null
        }
    }

    // Valida o token
    fun validarToken(token: String): Boolean {
        return try {
            Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
            true
        } catch (e: Exception) {
            false
        }
    }
}