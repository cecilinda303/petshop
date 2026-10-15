package com.petshop.controller

import com.petshop.entity.Tutor
import com.petshop.service.TutorService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/tutores")
class TutorController(private val tutorService: TutorService) {

    // GET - listar todos
    @GetMapping
    fun listarTodos(): List<Tutor> = tutorService.listarTodos()

    // GET - buscar por id
    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): ResponseEntity<Tutor> {
        val tutor = tutorService.buscarPorId(id)
        return if (tutor != null) ResponseEntity.ok(tutor)
        else ResponseEntity.notFound().build()
    }

    // GET - busca parcial por nome
    @GetMapping("/buscar")
    fun buscarPorNome(@RequestParam nome: String): List<Tutor> =
        tutorService.buscarPorNome(nome)

    // POST - criar
    @PostMapping
    fun criar(@RequestBody tutor: Tutor): ResponseEntity<Tutor> {
        val novo = tutorService.criar(tutor)
        return ResponseEntity.status(201).body(novo)
    }

    // PUT - atualizar
    @PutMapping("/{id}")
    fun atualizar(@PathVariable id: Long, @RequestBody tutor: Tutor): ResponseEntity<Tutor> {
        val atualizado = tutorService.atualizar(id, tutor)
        return if (atualizado != null) ResponseEntity.ok(atualizado)
        else ResponseEntity.notFound().build()
    }

    // DELETE - remover
    @DeleteMapping("/{id}")
    fun remover(@PathVariable id: Long): ResponseEntity<Void> {
        val removido = tutorService.remover(id)
        return if (removido) ResponseEntity.noContent().build()
        else ResponseEntity.notFound().build()
    }
}