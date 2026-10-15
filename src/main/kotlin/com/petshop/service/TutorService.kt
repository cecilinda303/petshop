package com.petshop.service

import com.petshop.entity.Tutor
import com.petshop.repository.TutorRepository
import org.springframework.stereotype.Service

@Service
class TutorService(private val tutorRepository: TutorRepository) {

    // CREATE
    fun criar(tutor: Tutor): Tutor = tutorRepository.save(tutor)

    // READ - listar todos
    fun listarTodos(): List<Tutor> = tutorRepository.findAll()

    // READ - buscar por id
    fun buscarPorId(id: Long): Tutor? = tutorRepository.findById(id).orElse(null)

    // READ - busca parcial por nome (JPQL LIKE)
    fun buscarPorNome(nome: String): List<Tutor> = tutorRepository.buscarPorNome(nome)

    // UPDATE
    fun atualizar(id: Long, tutorAtualizado: Tutor): Tutor? {
        val tutorExistente = tutorRepository.findById(id).orElse(null) ?: return null
        tutorExistente.nome = tutorAtualizado.nome
        tutorExistente.cpf = tutorAtualizado.cpf
        tutorExistente.email = tutorAtualizado.email
        tutorExistente.telefone = tutorAtualizado.telefone
        tutorExistente.dataCadastro = tutorAtualizado.dataCadastro
        tutorExistente.ativo = tutorAtualizado.ativo
        return tutorRepository.save(tutorExistente)
    }

    // DELETE
    fun remover(id: Long): Boolean {
        if (!tutorRepository.existsById(id)) return false
        tutorRepository.deleteById(id)
        return true
    }
}