package com.petshop.mapper

import com.petshop.dto.TutorRequest
import com.petshop.dto.TutorResponse
import com.petshop.entity.Tutor
import org.springframework.stereotype.Component

@Component
class TutorMapper {

    fun toEntity(request: TutorRequest): Tutor = Tutor(
        nome = request.nome,
        cpf = request.cpf,
        email = request.email,
        telefone = request.telefone,
        dataCadastro = request.dataCadastro!!,
        ativo = request.ativo!!
    )

    fun toResponse(entity: Tutor): TutorResponse = TutorResponse(
        id = entity.id,
        nome = entity.nome,
        cpf = entity.cpf,
        email = entity.email,
        telefone = entity.telefone,
        dataCadastro = entity.dataCadastro,
        ativo = entity.ativo
    )
}