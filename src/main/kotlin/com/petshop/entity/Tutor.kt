package com.petshop.entity

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "tutor")
class Tutor(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false, length = 100)
    var nome: String = "",

    @Column(nullable = false, length = 14, unique = true)
    var cpf: String = "",

    @Column(nullable = false, length = 100)
    var email: String = "",

    @Column(nullable = false, length = 20)
    var telefone: String = "",

    @Column(nullable = false)
    var dataCadastro: LocalDate = LocalDate.now(),

    @Column(nullable = false)
    var ativo: Boolean = true
)