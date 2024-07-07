package com.example.dcs.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.math.BigDecimal
import java.time.LocalDateTime


@Entity
data class ChargeDataRecord(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,
    val chargingSessionId: String,
    val vehicleId: String,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime,
    val totalCost: Double
)