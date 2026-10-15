package com.petshop.controller

import com.petshop.entity.Pet
import com.petshop.service.PetService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/pets")
class PetController(private val petService: PetService) {

    // GET - listar todos
    @GetMapping
    fun listarTodos(): List<Pet> = petService.listarTodos()

    // GET - buscar por id
    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): ResponseEntity<Pet> {
        val pet = petService.buscarPorId(id)
        return if (pet != null) ResponseEntity.ok(pet)
        else ResponseEntity.notFound().build()
    }

    // GET - busca parcial por nome
    @GetMapping("/buscar")
    fun buscarPorNome(@RequestParam nome: String): List<Pet> =
        petService.buscarPorNome(nome)

    // POST - criar
    @PostMapping
    fun criar(@RequestBody pet: Pet): ResponseEntity<Pet> {
        val novo = petService.criar(pet)
        return ResponseEntity.status(201).body(novo)
    }

    // PUT - atualizar
    @PutMapping("/{id}")
    fun atualizar(@PathVariable id: Long, @RequestBody pet: Pet): ResponseEntity<Pet> {
        val atualizado = petService.atualizar(id, pet)
        return if (atualizado != null) ResponseEntity.ok(atualizado)
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