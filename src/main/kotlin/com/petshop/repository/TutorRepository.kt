package com.petshop.repository

import com.petshop.entity.Tutor
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface TutorRepository : JpaRepository<Tutor, Long> {

   
    @Query("SELECT t FROM Tutor t WHERE LOWER(t.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
    fun buscarPorNome(@Param("nome") nome: String): List<Tutor>

    @Query("SELECT t FROM Tutor t WHERE LOWER(t.email) LIKE LOWER(CONCAT('%', :email, '%'))")
    fun buscarPorEmail(@Param("email") email: String): List<Tutor>
}