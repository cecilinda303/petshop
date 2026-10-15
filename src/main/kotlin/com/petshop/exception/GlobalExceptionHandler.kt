package com.petshop.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationErrors(ex: MethodArgumentNotValidException): ResponseEntity<Map<String, String>> {
        val erros = mutableMapOf<String, String>()
        ex.bindingResult.fieldErrors.forEach { erro ->
            erros[erro.field] = erro.defaultMessage ?: "Campo inválido"
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros)
    }
}