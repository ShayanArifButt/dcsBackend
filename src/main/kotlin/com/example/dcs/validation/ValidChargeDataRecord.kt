package com.example.dcs.validation

import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@MustBeDocumented
@Constraint(validatedBy = [ValidChargeDataRecordValidator::class])
@Target(AnnotationTarget.CLASS, AnnotationTarget.FILE)
@Retention(AnnotationRetention.RUNTIME)
annotation class ValidChargeDataRecord(
    val message: String = "Invalid charge data record",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)