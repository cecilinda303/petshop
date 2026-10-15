package com.petshop.dto

data class AuthResponse(
    val token: String,
    val tipo: String = "Bearer"
)