package com.tatsuaki.carestandardform.domain.serviceplan

import com.tatsuaki.carestandardform.domain.model.*
import com.tatsuaki.carestandardform.util.JapaneseDate
import org.springframework.web.multipart.MultipartFile
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalTime
import java.time.YearMonth

class ServicePlan(
    val serviceProvisionYearMonth: YearMonth,
    val creationDate: LocalDate,
    val insure: Insure,
    val careManager: CareManager,
    val insuredPerson: InsuredPerson,
    val notificationDate: LocalDate,
    val shortStayUseDaysOfPreviousMonth: Int,
    val providedServices: List<ProvidedService>
)