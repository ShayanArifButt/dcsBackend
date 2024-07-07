package com.example.dcs.service

import com.example.dcs.dto.ChargeDataRecordDTO
import com.example.dcs.exception.ResourceNotFoundException
import com.example.dcs.mapper.toDTO
import com.example.dcs.mapper.toEntity
import com.example.dcs.model.ChargeDataRecord
import com.example.dcs.repository.ChargeDataRecordRepository
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class ChargeDataRecordService(private val chargeDataRecordRepository: ChargeDataRecordRepository) {

    fun createChargeDataRecord(chargeDataRecordDTO: ChargeDataRecordDTO): ChargeDataRecordDTO {
        val entity = chargeDataRecordDTO.toEntity()
        return chargeDataRecordRepository.save(entity).toDTO()
    }

    fun getChargeDataRecordById(id: Long): ChargeDataRecordDTO {
        val entity = chargeDataRecordRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("ChargeDataRecord not found with id $id") }
        return entity.toDTO()
    }
}