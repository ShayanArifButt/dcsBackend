package com.example.dcs

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DcsApplication

fun main(args: Array<String>) {
	runApplication<DcsApplication>(*args)
}
