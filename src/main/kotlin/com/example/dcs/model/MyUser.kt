package com.example.dcs.model
import jakarta.persistence.*


@Entity
@Table(name = "users")
data class MyUser(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Column(unique = true)
    val username: String,
    val password: String
)
