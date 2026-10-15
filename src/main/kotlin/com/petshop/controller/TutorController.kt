package com.petshop.controller

import com.petshop.dto.TutorRequest
import com.petshop.dto.TutorResponse
import com.petshop.mapper.TutorMapper
import com.petshop.service.TutorService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/tutores")
class TutorController(
    private val tutorService: TutorService,
    private val tutorMapper: TutorMapper
) {

    // GET - listar todos
    @GetMapping
    fun listarTodos(): List<TutorResponse> =
        tutorService.listarTodos().map { tutorMapper.toResponse(it) }

    // GET - buscar por id
    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): ResponseEntity<TutorResponse> {
        val tutor = tutorService.buscarPorId(id)
        return if (tutor != null) ResponseEntity.ok(tutorMapper.toResponse(tutor))
        else ResponseEntity.notFound().build()
    }

    // GET - busca parcial por nome
    @GetMapping("/buscar")
    fun buscarPorNome(@RequestParam nome: String): List<TutorResponse> =
        tutorService.buscarPorNome(nome).map { tutorMapper.toResponse(it) }

    // POST - criar
    @PostMapping
    fun criar(@Valid @RequestBody request: TutorRequest): ResponseEntity<TutorResponse> {
        val tutor = tutorMapper.toEntity(request)
        val salvo = tutorService.criar(tutor)
        return ResponseEntity.status(201).body(tutorMapper.toResponse(salvo))
    }

    // PUT - atualizar
    @PutMapping("/{id}")
    fun atualizar(
        @PathVariable id: Long,
        @Valid @RequestBody request: TutorRequest
    ): ResponseEntity<TutorResponse> {
        val tutor = tutorMapper.toEntity(request)
        val atualizado = tutorService.atualizar(id, tutor)
        return if (atualizado != null) ResponseEntity.ok(tutorMapper.toResponse(atualizado))
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