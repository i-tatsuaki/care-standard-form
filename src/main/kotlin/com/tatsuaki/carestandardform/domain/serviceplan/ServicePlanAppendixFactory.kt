package com.tatsuaki.carestandardform.domain.serviceplan

import com.tatsuaki.carestandardform.domain.ServiceProvisionYearMonth
import com.tatsuaki.carestandardform.domain.model.*
import com.tatsuaki.carestandardform.domain.model.csv.InsuredPersonAppendixCsvLine
import com.tatsuaki.carestandardform.domain.model.csv.ServicePlanAppendixCsvLine
import com.tatsuaki.carestandardform.domain.serviceplanappendix.ServicePlanAppendix
import com.tatsuaki.carestandardform.domain.serviceplanappendix.TotalUnitNumber
import com.tatsuaki.carestandardform.domain.serviceplanappendix.UserCountShortStay
import com.tatsuaki.carestandardform.util.JapaneseDate
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalTime
import java.time.YearMonth
import kotlin.streams.toList

class ServicePlanAppendixFactory {
    fun createServicePlanAppendixes(
        insuredPersonAppendixCsvLines: ArrayList<InsuredPersonAppendixCsvLine>,
        servicePlanCsvAppendixLines: ArrayList<ServicePlanAppendixCsvLine>
    ): List<ServicePlanAppendix> {
        val servicePlanAppendixes: ArrayList<ServicePlanAppendix> = arrayListOf()
        insuredPersonAppendixCsvLines.stream().forEach { insuredPersonAppendixCsvLine ->
            // 利用者補足の１行に対応する６票の行を抽出
            val targetServicePlanAppendixes = servicePlanCsvAppendixLines.stream().filter {
                it.isCorrespond(insuredPersonAppendixCsvLine)
            }.toList()
            servicePlanAppendixes.add(create(insuredPersonAppendixCsvLine, targetServicePlanAppendixes))
        }
        return servicePlanAppendixes
    }

    private fun create(
        insuredPersonAppendixCsvLine: InsuredPersonAppendixCsvLine,
        servicePlanAppendixCsvLine: List<ServicePlanAppendixCsvLine>
    ): ServicePlanAppendix {
        val time = ProvidedTime.Time(
            LocalTime.of(10, 0),
            LocalTime.of(12, 0)
        )
        return ServicePlanAppendix(
            // TODO 仮実装
            ServiceProvisionYearMonth(YearMonth.of(2020, 4)),
            LocalDate.of(2020, 3, 1),
            InsuredPerson(
                "利用者氏名",
                "リヨウシャシメイ",
                Sex.fromDivision("1"),
                JapaneseDate(LocalDate.of(1988, 12, 22)),
                InsureLicense(
                    "H123456789",
                    CareLevel.要介護３,
                    "要介護1",
                    LocalDate.of(2019, 11, 30),
                    16592,
                    LocalDate.of(2018, 12, 1),
                    LocalDate.of(2019, 12, 1)
                )
            ),
            ProvidedServices(
                listOf(
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
                            .addPlan(            ProvidedTime.Time(
                                LocalTime.of(10, 0),
                                LocalTime.of(12, 0)
                            )
                                , 1)
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
            ),
            16390,
            TotalUnitNumber(1, 2, 3, 4, 5, 6, 7, 8, 9),
            mapOf(
                CareServiceKind.HOME_VISIT_CARE to KindUnitNumberManagement(1, 2, 3),
                CareServiceKind.HOME_VISIT_BATHING_CARE to KindUnitNumberManagement(4, 5, 6),
                CareServiceKind.HOME_VISIT_NURSING_CARE to KindUnitNumberManagement(7, 8, 9),
                CareServiceKind.HOME_VISIT_REHABILITATION to KindUnitNumberManagement(10, 11, 12),
                CareServiceKind.DAY_CARE to KindUnitNumberManagement(13, 14, 15),
                CareServiceKind.DAY_CARE_FOR_DEMENTIA to KindUnitNumberManagement(16, 17, 18),
                CareServiceKind.LIFE_CARE_FOR_DEMENTIA to KindUnitNumberManagement(19, 20, 21),
                CareServiceKind.DAY_REHABILITATION to KindUnitNumberManagement(22, 23, 24),
                CareServiceKind.RENTAL_CARE_EQUIPMENT to KindUnitNumberManagement(25, 26, 27),
                CareServiceKind.SHORT_TERM_LIFE_CARE to KindUnitNumberManagement(28, 29, 30),
                CareServiceKind.SHORT_TERM_MEDICAL_CARE to KindUnitNumberManagement(31, 32, 33),
                CareServiceKind.NIGHTLY_HOME_VISIT_CARE to KindUnitNumberManagement(34, 35, 36),
                CareServiceKind.MULTIFUNCTIONAL_HOME_CARE to KindUnitNumberManagement(37, 38, 39),
                CareServiceKind.REGULAR_PATROL to KindUnitNumberManagement(40, 41, 42),
                CareServiceKind.COMPOSITE_SERVICE to KindUnitNumberManagement(43, 44, 45)
            ),
            KindUnitNumberManagement(46, 47, 48),
            UserCountShortStay(5, 10, 15)
        )
    }
}