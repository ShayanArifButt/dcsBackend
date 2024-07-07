package com.example.dcs.repository

import com.example.dcs.model.ChargeDataRecord
import org.springframework.data.jpa.repository.JpaRepository

interface ChargeDataRecordRepository : JpaRepository<ChargeDataRecord, Long> {
    fun findByVehicleId(vehicleId: String): List<ChargeDataRecord>
    fun findTopByVehicleIdOrderByEndTimeDesc(vehicleId: String): ChargeDataRecord?
}