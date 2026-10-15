package com.petshop.mapper

import com.petshop.dto.PetRequest
import com.petshop.dto.PetResponse
import com.petshop.entity.Pet
import org.springframework.stereotype.Component

@Component
class PetMapper {

    fun toEntity(request: PetRequest): Pet = Pet(
        nome = request.nome,
        especie = request.especie,
        raca = request.raca,
        peso = request.peso!!,
        dataNascimento = request.dataNascimento!!,
        vacinado = request.vacinado!!
    )

    fun toResponse(entity: Pet): PetResponse = PetResponse(
        id = entity.id,
        nome = entity.nome,
        especie = entity.especie,
        raca = entity.raca,
        peso = entity.peso,
        dataNascimento = entity.dataNascimento,
        vacinado = entity.vacinado
    )
}