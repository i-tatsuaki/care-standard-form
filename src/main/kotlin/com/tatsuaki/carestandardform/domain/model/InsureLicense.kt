package com.tatsuaki.carestandardform.domain.model

import java.time.LocalDate

data class InsureLicense(
    val number: String,
    val careLevel: CareLevel,
    val previousCareLevel: CareLevel,
    val careLevelUpdateDate: LocalDate,
    val creditLimit: Int,
    val creditLimitFrom: LocalDate,
    val creditLimitTo: LocalDate
)
