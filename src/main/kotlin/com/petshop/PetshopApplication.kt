package com.petshop

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PetshopApplication

fun main(args: Array<String>) {
	runApplication<PetshopApplication>(*args)
}
