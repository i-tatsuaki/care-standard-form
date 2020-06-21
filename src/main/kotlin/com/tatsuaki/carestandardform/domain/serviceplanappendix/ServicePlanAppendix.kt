package com.tatsuaki.carestandardform.domain.serviceplanappendix

import com.tatsuaki.carestandardform.domain.ServiceProvisionYearMonth
import com.tatsuaki.carestandardform.domain.model.*
import com.tatsuaki.carestandardform.util.JapaneseDate
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalTime
import java.time.YearMonth

class ServicePlanAppendix (
    val serviceProvisionYearMonth: ServiceProvisionYearMonth,
    val creationDate: LocalDate,
    val insuredPerson: InsuredPerson,
    val providedServices: ProvidedServices,
    val limitDivisionUnitNumber: Int,
    val totalUnitNumber: TotalUnitNumber,
    val kindUnitNumberManagements: List<KindUnitNumberManagement>,
    val totalUnitNumberManagement: TotalUnitNumberManagement,
    val useCountShortStay: UserCountShortStay
)
