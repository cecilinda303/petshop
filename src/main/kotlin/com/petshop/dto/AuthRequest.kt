package com.petshop.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class AuthRequest(

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    val email: String = "",

    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres")
    val senha: String = ""
)