package com.tatsuaki.carestandardform.domain.model

import java.time.LocalDate

data class InsureLicense(
    val number: String,
    val careLevel: String,
    val previousCareLevel: String,
    val careLevelUpdateDate: LocalDate,
    val creditLimit: Int,
    val creditLimitFrom: LocalDate,
    val creditLimitTo: LocalDate
)
