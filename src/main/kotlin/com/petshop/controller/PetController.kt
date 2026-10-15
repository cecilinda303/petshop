package com.petshop.controller

import com.petshop.dto.PetRequest
import com.petshop.dto.PetResponse
import com.petshop.mapper.PetMapper
import com.petshop.service.PetService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/pets")
class PetController(
    private val petService: PetService,
    private val petMapper: PetMapper
) {

    // GET - listar todos
    @GetMapping
    fun listarTodos(): List<PetResponse> =
        petService.listarTodos().map { petMapper.toResponse(it) }

    // GET - buscar por id
    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): ResponseEntity<PetResponse> {
        val pet = petService.buscarPorId(id)
        return if (pet != null) ResponseEntity.ok(petMapper.toResponse(pet))
        else ResponseEntity.notFound().build()
    }

    // GET - busca parcial por nome
    @GetMapping("/buscar")
    fun buscarPorNome(@RequestParam nome: String): List<PetResponse> =
        petService.buscarPorNome(nome).map { petMapper.toResponse(it) }

    // POST - criar
    @PostMapping
    fun criar(@Valid @RequestBody request: PetRequest): ResponseEntity<PetResponse> {
        val pet = petMapper.toEntity(request)
        val salvo = petService.criar(pet)
        return ResponseEntity.status(201).body(petMapper.toResponse(salvo))
    }

    // PUT - atualizar
    @PutMapping("/{id}")
    fun atualizar(
        @PathVariable id: Long,
        @Valid @RequestBody request: PetRequest
    ): ResponseEntity<PetResponse> {
        val pet = petMapper.toEntity(request)
        val atualizado = petService.atualizar(id, pet)
        return if (atualizado != null) ResponseEntity.ok(petMapper.toResponse(atualizado))
        else ResponseEntity.notFound().build()
    }

    // DELETE - remover
    @DeleteMapping("/{id}")
    fun remover(@PathVariable id: Long): ResponseEntity<Void> {
        val removido = petService.remover(id)
        return if (removido) ResponseEntity.noContent().build()
        else ResponseEntity.notFound().build()
    }
}