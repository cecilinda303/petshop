package com.petshop.dto

import jakarta.validation.constraints.*
import java.time.LocalDate

data class TutorRequest(

    @field:NotBlank(message = "Nome é obrigatório")
    @field:Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres")
    val nome: String = "",

    @field:NotBlank(message = "CPF é obrigatório")
    @field:Pattern(regexp = "\\d{11}", message = "CPF deve conter 11 dígitos numéricos")
    val cpf: String = "",

    @field:NotBlank(message = "Email é obrigatório")
    @field:Email(message = "Email inválido")
    val email: String = "",

    @field:NotBlank(message = "Telefone é obrigatório")
    @field:Size(min = 10, max = 20, message = "Telefone deve ter entre 10 e 20 caracteres")
    val telefone: String = "",

    @field:NotNull(message = "Data de cadastro é obrigatória")
    @field:PastOrPresent(message = "Data de cadastro não pode ser futura")
    val dataCadastro: LocalDate? = null,

    @field:NotNull(message = "Ativo é obrigatório")
    val ativo: Boolean? = null
)