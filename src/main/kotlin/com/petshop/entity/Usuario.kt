package com.petshop.entity

import jakarta.persistence.*

@Entity
@Table(name = "usuario")
class Usuario(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false, unique = true, length = 100)
    var email: String = "",

    @Column(nullable = false, length = 100)
    var senha: String = "",

    @Column(nullable = false, length = 50)
    var role: String = "USER"
)