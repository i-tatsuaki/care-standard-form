package com.tatsuaki.carestandardform.domain.serviceplanappendix

import com.tatsuaki.carestandardform.domain.model.*
import com.tatsuaki.carestandardform.util.JapaneseDate
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalTime
import java.time.YearMonth

class ServicePlanAppendix {

    val serviceProvisionYearMonth: YearMonth
    val creationDate: LocalDate
    val insuredPerson: InsuredPerson
    val providedServices: List<ProvidedService>

    init {
        // TODO 仮実装
        this.serviceProvisionYearMonth = YearMonth.of(2020, 4)
        this.creationDate = LocalDate.of(2020, 3, 1)
        this.insuredPerson = InsuredPerson(
            "利用者氏名",
            "リヨウシャシメイ",
            "男",
            JapaneseDate(LocalDate.of(1988, 12, 22)),
            InsureLicense(
                "H123456789",
                "要介護3",
                "要介護1",
                LocalDate.of(2019, 11, 30),
                16592,
                LocalDate.of(2018, 12, 1),
                LocalDate.of(2019, 12, 1)
            )
        )

        val time = ProvidedTime.Time(
            LocalTime.of(10, 0),
            LocalTime.of(12, 0)
        )
        this.providedServices = listOf(
            ProvidedService(
                CareService("通所介護１", "131111", 132, 90, 119, 488, 30, 458, 58, 400, BigDecimal(10.0), 4880, 90, 4392, 488, 488),
                Office("とある通所介護事業所", "1234567890", "03-1234-5678"),
                ProvidedTime()
                    .addPlan(time, 1)
                    .addPlan(time, 3)
                    .addPlan(time, 5)
                    .addResult(time, 1)
                    .addResult(time, 5)
            ),
            ProvidedService(
                CareService("通所介護２", "131111", 132, 90, 119, 488, 30, 458, 58, 400, BigDecimal(10.0), 4880, 90, 4392, 488, 488),
                Office("とある通所介護事業所", "1234567890", "03-1234-5678"),
                ProvidedTime()
                    .addPlan(time, 2)
                    .addPlan(time, 4)
                    .addPlan(time, 6)
                    .addResult(time, 2)
                    .addResult(time, 4)
            ),
            ProvidedService(
                CareService("通所介護３", "131111", 132, 90, 119, 488, 30, 458, 58, 400, BigDecimal(10.0), 4880, 90, 4392, 488, 488),
                Office("とある通所介護事業所", "1234567890", "03-1234-5678"),
                ProvidedTime()
                    .addPlan(time, 3)
                    .addPlan(time, 6)
                    .addPlan(time, 9)
                    .addResult(time, 3)
                    .addResult(time, 9)
            )
        )
    }


}
