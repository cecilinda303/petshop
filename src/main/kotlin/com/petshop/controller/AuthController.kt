package com.petshop.controller

import com.petshop.dto.AuthRequest
import com.petshop.dto.AuthResponse
import com.petshop.service.AuthService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AuthController(private val authService: AuthService) {

    // POST /auth/signup
    @PostMapping("/signup")
    fun signup(@Valid @RequestBody request: AuthRequest): ResponseEntity<AuthResponse> {
        return try {
            ResponseEntity.ok(authService.signup(request))
        } catch (e: RuntimeException) {
            ResponseEntity.badRequest().build()
        }
    }

    // POST /auth/signin
    @PostMapping("/signin")
    fun signin(@Valid @RequestBody request: AuthRequest): ResponseEntity<AuthResponse> {
        return try {
            ResponseEntity.ok(authService.signin(request))
        } catch (e: RuntimeException) {
            ResponseEntity.status(401).build()
        }
    }
}