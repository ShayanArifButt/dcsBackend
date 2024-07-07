package com.example.dcs.validation

import com.example.dcs.dto.ChargeDataRecordDTO
import com.example.dcs.repository.ChargeDataRecordRepository
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class ValidChargeDataRecordValidator(
    private val chargeDataRecordRepository: ChargeDataRecordRepository
) : ConstraintValidator<ValidChargeDataRecord, ChargeDataRecordDTO> {
    override fun isValid(value: ChargeDataRecordDTO, context: ConstraintValidatorContext): Boolean {
        var isValid = true

        if (value.endTime.isBefore(value.startTime)) {
            context.disableDefaultConstraintViolation()
            context.buildConstraintViolationWithTemplate("End time cannot be before start time")
                .addPropertyNode("endTime").addConstraintViolation()
            isValid = false
        }

        // Checking if the start time of the new record is after the end time of the last record for the vehicle
        val lastRecord = chargeDataRecordRepository.findTopByVehicleIdOrderByEndTimeDesc(value.vehicleId)
        if (lastRecord != null && value.startTime.isBefore(lastRecord.endTime)) {
            context.disableDefaultConstraintViolation()
            context.buildConstraintViolationWithTemplate("Start time must be after the end time of the last record")
                .addPropertyNode("startTime").addConstraintViolation()
            isValid = false
        }

        return isValid
    }
}