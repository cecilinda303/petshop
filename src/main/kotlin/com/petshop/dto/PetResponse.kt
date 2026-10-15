package com.petshop.dto

import java.time.LocalDate

data class PetResponse(
    val id: Long?,
    val nome: String,
    val especie: String,
    val raca: String,
    val peso: Double,
    val dataNascimento: LocalDate,
    val vacinado: Boolean
)