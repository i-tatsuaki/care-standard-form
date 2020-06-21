package com.tatsuaki.carestandardform.domain.serviceplan

class TotalUnitNumber (
    val totalUnitNumber: Int,
    val overKindUnitNumber: Int,
    val kindUnitNumber: Int,
    val overDivisionUnitNumber: Int,
    val divisionUnitNumber: Int,
    val totalInsuranceDemand: Int,
    val paidInsuranceDemandFromInsurance: Int,
    val paidInsuranceDemandFromInsuredPerson: Int,
    val totalInsuredPersonDemand: Int
)
