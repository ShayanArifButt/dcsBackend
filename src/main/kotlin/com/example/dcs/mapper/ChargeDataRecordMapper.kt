package com.example.dcs.mapper

import com.example.dcs.dto.ChargeDataRecordDTO
import com.example.dcs.model.ChargeDataRecord

fun ChargeDataRecord.toDTO(): ChargeDataRecordDTO = ChargeDataRecordDTO(
    chargingSessionId = this.chargingSessionId,
    vehicleId = this.vehicleId,
    startTime = this.startTime,
    endTime = this.endTime,
    totalCost = this.totalCost
)

fun ChargeDataRecordDTO.toEntity(): ChargeDataRecord = ChargeDataRecord(
    chargingSessionId = this.chargingSessionId,
    vehicleId = this.vehicleId,
    startTime = this.startTime,
    endTime = this.endTime,
    totalCost = this.totalCost
)