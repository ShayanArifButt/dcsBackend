package com.example.dcs.controller

import com.example.dcs.dto.ChargeDataRecordDTO
import com.example.dcs.model.ChargeDataRecord
import com.example.dcs.service.ChargeDataRecordService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/cdr")
class ChargeDataRecordController(private val chargeDataRecordService: ChargeDataRecordService) {

    @PostMapping
    fun createChargeDataRecord(@Valid @RequestBody chargeDataRecordDTO: ChargeDataRecordDTO): ResponseEntity<ChargeDataRecordDTO> {
        val createdRecord = chargeDataRecordService.createChargeDataRecord(chargeDataRecordDTO)
        return ResponseEntity(createdRecord, HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun getChargeDataRecordById(@PathVariable id: Long): ResponseEntity<ChargeDataRecordDTO> {
        val record = chargeDataRecordService.getChargeDataRecordById(id)
        return ResponseEntity(record, HttpStatus.OK)
    }
}