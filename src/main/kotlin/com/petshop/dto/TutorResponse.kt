package com.petshop.dto

import java.time.LocalDate

data class TutorResponse(
    val id: Long?,
    val nome: String,
    val cpf: String,
    val email: String,
    val telefone: String,
    val dataCadastro: LocalDate,
    val ativo: Boolean
)