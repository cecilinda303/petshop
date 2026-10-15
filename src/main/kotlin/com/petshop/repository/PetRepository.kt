package com.petshop.repository

import com.petshop.entity.Pet
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface PetRepository : JpaRepository<Pet, Long> {

    
    @Query("SELECT p FROM Pet p WHERE LOWER(p.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
    fun buscarPorNome(@Param("nome") nome: String): List<Pet>

 
    @Query("SELECT p FROM Pet p WHERE LOWER(p.especie) LIKE LOWER(CONCAT('%', :especie, '%'))")
    fun buscarPorEspecie(@Param("especie") especie: String): List<Pet>
}