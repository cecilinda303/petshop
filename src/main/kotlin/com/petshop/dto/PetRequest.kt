package com.petshop.dto

import jakarta.validation.constraints.*
import java.time.LocalDate

data class PetRequest(

    @field:NotBlank(message = "Nome é obrigatório")
    @field:Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres")
    val nome: String = "",

    @field:NotBlank(message = "Espécie é obrigatória")
    @field:Size(max = 50, message = "Espécie deve ter no máximo 50 caracteres")
    val especie: String = "",

    @field:NotBlank(message = "Raça é obrigatória")
    val raca: String = "",

    @field:Positive(message = "Peso deve ser maior que zero")
    @field:NotNull(message = "Peso é obrigatório")
    val peso: Double? = null,

    @field:NotNull(message = "Data de nascimento é obrigatória")
    @field:PastOrPresent(message = "Data de nascimento não pode ser futura")
    val dataNascimento: LocalDate? = null,

    @field:NotNull(message = "Vacinado é obrigatório")
    val vacinado: Boolean? = null
)