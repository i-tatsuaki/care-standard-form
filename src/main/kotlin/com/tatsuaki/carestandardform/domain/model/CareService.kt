package com.tatsuaki.carestandardform.domain.model

import java.math.BigDecimal

data class CareService(
    val name: String,
    val code: String,
    val unitNumber: Int,
    val discountRate: Int,
    val discountedUnitNumber: Int,
    val totalUnitNumber: Int,
    val overKindUnitNumber: Int,
    val kindUnitNumber: Int,
    val overDivisionUnitNumber: Int,
    val divisionUnitNumber: Int,
    val unitPrice: BigDecimal,
    val totalInsuranceDemand: Int,
    val benefitsRate: Int,
    val paidInsuranceDemandFromInsurance: Int,
    val paidInsuranceDemandFromInsuredPerson: Int,
    val totalInsuredPersonDemand: Int
)