package com.petshop.service

import com.petshop.entity.Pet
import com.petshop.repository.PetRepository
import org.springframework.stereotype.Service

@Service
class PetService(private val petRepository: PetRepository) {

    // CREATE
    fun criar(pet: Pet): Pet = petRepository.save(pet)

    // READ - listar todos
    fun listarTodos(): List<Pet> = petRepository.findAll()

    // READ - buscar por id
    fun buscarPorId(id: Long): Pet? = petRepository.findById(id).orElse(null)

    // READ - busca parcial por nome (JPQL LIKE)
    fun buscarPorNome(nome: String): List<Pet> = petRepository.buscarPorNome(nome)

    // UPDATE
    fun atualizar(id: Long, petAtualizado: Pet): Pet? {
        val petExistente = petRepository.findById(id).orElse(null) ?: return null
        petExistente.nome = petAtualizado.nome
        petExistente.especie = petAtualizado.especie
        petExistente.raca = petAtualizado.raca
        petExistente.peso = petAtualizado.peso
        petExistente.dataNascimento = petAtualizado.dataNascimento
        petExistente.vacinado = petAtualizado.vacinado
        return petRepository.save(petExistente)
    }

    // DELETE
    fun remover(id: Long): Boolean {
        if (!petRepository.existsById(id)) return false
        petRepository.deleteById(id)
        return true
    }
}