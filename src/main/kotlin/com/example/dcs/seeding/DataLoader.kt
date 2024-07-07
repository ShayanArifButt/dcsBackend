package com.example.dcs.seeding

import com.example.dcs.model.MyUser
import com.example.dcs.repository.UserRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
class DataLoader {

    @Bean
    fun init(userRepository: UserRepository, passwordEncoder: BCryptPasswordEncoder) = CommandLineRunner {
        val username = "testUser"
        val password = "testPassword"
        if (userRepository.findByUsername(username) == null) {
            val user = MyUser(username = username, password = passwordEncoder.encode(password))
            userRepository.save(user)
            println("user seeded: $username")
        } else {
            println("user already exists: $username")
        }
    }
}