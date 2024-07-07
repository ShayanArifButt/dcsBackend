package com.example.dcs.repository

import com.example.dcs.model.MyUser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<MyUser, Long> {
    fun findByUsername(username: String): MyUser?
}