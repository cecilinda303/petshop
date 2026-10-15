package com.petshop.entity

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "pet")
class Pet(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false, length = 100)
    var nome: String = "",

    @Column(nullable = false, length = 50)
    var especie: String = "",

    @Column(nullable = false, length = 50)
    var raca: String = "",

    @Column(nullable = false)
    var peso: Double = 0.0,

    @Column(nullable = false)
    var dataNascimento: LocalDate = LocalDate.now(),

    @Column(nullable = false)
    var vacinado: Boolean = false
)