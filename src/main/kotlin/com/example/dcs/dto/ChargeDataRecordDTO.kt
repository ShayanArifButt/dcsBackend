package com.example.dcs.dto

import com.example.dcs.validation.ValidChargeDataRecord
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import java.math.BigDecimal
import java.time.LocalDateTime
import java.time.ZonedDateTime

@ValidChargeDataRecord
data class ChargeDataRecordDTO(
    @field:NotNull(message = "Charging session ID is required")
    val chargingSessionId: String,

    @field:NotNull(message = "Vehicle ID is required")
    val vehicleId: String,

    @field:NotNull(message = "Start time is required")
    val startTime: LocalDateTime,

    @field:NotNull(message = "End time is required")
    val endTime: LocalDateTime,

    @field:Positive(message = "Total cost must be greater than zero")
    val totalCost: Double
)