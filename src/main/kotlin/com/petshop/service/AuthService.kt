package com.petshop.service

import com.petshop.dto.AuthRequest
import com.petshop.dto.AuthResponse
import com.petshop.entity.Usuario
import com.petshop.repository.UsuarioRepository
import com.petshop.security.JwtUtil
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val usuarioRepository: UsuarioRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtUtil: JwtUtil
) {

    fun signup(request: AuthRequest): AuthResponse {
        val email: String = request.email
        val senha: String = request.senha

        if (usuarioRepository.existsByEmail(email)) {
            throw RuntimeException("Email já cadastrado")
        }

        val usuario = Usuario(
            email = email,
            senha = passwordEncoder.encode(senha)!!,
            role = "USER"
        )
        usuarioRepository.save(usuario)

        val token = jwtUtil.gerarToken(email)
        return AuthResponse(token = token)
    }

    fun signin(request: AuthRequest): AuthResponse {
        val email: String = request.email
        val senha: String = request.senha

        val usuario = usuarioRepository.findByEmail(email)
            ?: throw RuntimeException("Credenciais inválidas")

        if (!passwordEncoder.matches(senha, usuario.senha!!)) {
            throw RuntimeException("Credenciais inválidas")
        }

        val token = jwtUtil.gerarToken(email)
        return AuthResponse(token = token)
    }
}