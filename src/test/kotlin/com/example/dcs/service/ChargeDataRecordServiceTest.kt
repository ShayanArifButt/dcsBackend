package com.example.dcs.service

import com.example.dcs.dto.ChargeDataRecordDTO
import com.example.dcs.exception.ResourceNotFoundException
import com.example.dcs.mapper.toDTO
import com.example.dcs.mapper.toEntity
import com.example.dcs.model.ChargeDataRecord
import com.example.dcs.repository.ChargeDataRecordRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime
import java.util.*

@SpringBootTest
class ChargeDataRecordServiceTest {

    private val repository = mock(ChargeDataRecordRepository::class.java)
    private val service = ChargeDataRecordService(repository)

    @Test
    fun testCreateChargeDataRecord() {
        val dto = ChargeDataRecordDTO("session1", "vehicle1", LocalDateTime.now(), LocalDateTime.now().plusHours(1), 10.0)
        val entity = dto.toEntity()
        `when`(repository.save(any(ChargeDataRecord::class.java))).thenReturn(entity)

        val result = service.createChargeDataRecord(dto)

        assertEquals(dto.chargingSessionId, result.chargingSessionId)
        assertEquals(dto.vehicleId, result.vehicleId)
    }

    @Test
    fun testGetChargeDataRecordById_Success() {
        val entity = ChargeDataRecord(1L, "session1", "vehicle1", LocalDateTime.now(), LocalDateTime.now().plusHours(1), 10.0)
        `when`(repository.findById(1L)).thenReturn(Optional.of(entity))

        val result = service.getChargeDataRecordById(1L)

        assertEquals(entity.toDTO(), result)
    }

    @Test
    fun testGetChargeDataRecordById_NotFound() {
        `when`(repository.findById(1L)).thenReturn(Optional.empty())

        assertThrows<ResourceNotFoundException> {
            service.getChargeDataRecordById(1L)
        }
    }
}