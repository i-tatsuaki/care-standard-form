package com.tatsuaki.carestandardform.domain.serviceplan

import com.tatsuaki.carestandardform.domain.model.*
import com.tatsuaki.carestandardform.util.JapaneseDate
import org.springframework.web.multipart.MultipartFile
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalTime
import java.time.YearMonth

class ServicePlanFactory {
    fun create(insuredPersonAppendixFile: MultipartFile, servicePlanFile: MultipartFile): ServicePlan {

        // TODO 仮実装
        val serviceProvisionYearMonth = YearMonth.of(2020, 3)
        val creationDate = LocalDate.of(2020, 3, 1)
        val insure = Insure("123456", "江戸川区")
        val careManager = CareManager(
            "ケアマネ氏名",
            Office("ある居宅介護支援事業所", "1234567890", "03-1234-5678")
        )
        val insuredPerson = InsuredPerson(
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
        val notificationDate = LocalDate.of(2020, 1, 1)
        val shortStayUseDaysOfPreviousMonth = 13

        val time = ProvidedTime.Time(
            LocalTime.of(10, 0),
            LocalTime.of(12, 0)
        )
        val providedServices = listOf(
            ProvidedService(
                CareService(
                    "通所介護１",
                    "131111",
                    132,
                    90,
                    119,
                    488,
                    30,
                    458,
                    58,
                    400,
                    BigDecimal(10.0),
                    4880,
                    90,
                    4392,
                    488,
                    488
                ),
                Office("とある通所介護事業所", "1234567890", "03-1234-5678"),
                ProvidedTime()
                    .addPlan(time, 1)
                    .addPlan(time, 3)
                    .addPlan(time, 5)
                    .addResult(time, 1)
                    .addResult(time, 5)
            ),
            ProvidedService(
                CareService(
                    "通所介護２",
                    "131111",
                    132,
                    90,
                    119,
                    488,
                    30,
                    458,
                    58,
                    400,
                    BigDecimal(10.0),
                    4880,
                    90,
                    4392,
                    488,
                    488
                ),
                Office("とある通所介護事業所", "1234567890", "03-1234-5678"),
                ProvidedTime()
                    .addPlan(time, 2)
                    .addPlan(time, 4)
                    .addPlan(time, 6)
                    .addResult(time, 2)
                    .addResult(time, 4)
            ),
            ProvidedService(
                CareService(
                    "通所介護３",
                    "131111",
                    132,
                    90,
                    119,
                    488,
                    30,
                    458,
                    58,
                    400,
                    BigDecimal(10.0),
                    4880,
                    90,
                    4392,
                    488,
                    488
                ),
                Office("とある通所介護事業所", "1234567890", "03-1234-5678"),
                ProvidedTime()
                    .addPlan(time, 3)
                    .addPlan(time, 6)
                    .addPlan(time, 9)
                    .addResult(time, 3)
                    .addResult(time, 9)
            )
        )

        return ServicePlan(
            serviceProvisionYearMonth,
            creationDate,
            insure,
            careManager,
            insuredPerson,
            notificationDate,
            shortStayUseDaysOfPreviousMonth,
            providedServices
        )
    }
}