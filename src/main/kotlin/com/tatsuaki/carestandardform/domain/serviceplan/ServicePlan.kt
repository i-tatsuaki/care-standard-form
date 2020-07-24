package com.tatsuaki.carestandardform.domain.serviceplan

import com.tatsuaki.carestandardform.domain.ServiceProvisionYearMonth
import com.tatsuaki.carestandardform.domain.model.*
import java.time.LocalDate

class ServicePlan(
    val serviceProvisionYearMonth: ServiceProvisionYearMonth,
    val creationDate: LocalDate,
    val insure: Insure,
    val careManager: CareManager,
    val insuredPerson: InsuredPerson,
    val notificationDate: String,
    val shortStayUseDaysOfPreviousMonth: Int?,
    val providedServices: ProvidedServices,
    val limitDivisionUnitNumber: Int?,
    val totalUnitNumber: TotalUnitNumber,
    val kindUnitNumberManagements: List<KindUnitNumberManagement>,
    val useCountShortStay: UserCountShortStay
)