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
import java.math.MathContext
import java.time.LocalDate
import java.time.LocalTime
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import kotlin.streams.toList

class ServicePlanAppendixFactory {
    fun createServicePlanAppendixes(
        insuredPersonAppendixCsvLines: ArrayList<InsuredPersonAppendixCsvLine>,
        servicePlanCsvAppendixLines: ArrayList<ServicePlanAppendixCsvLine>
    ): List<ServicePlanAppendix> {
        val servicePlanAppendixes: ArrayList<ServicePlanAppendix> = arrayListOf()
        insuredPersonAppendixCsvLines.stream().forEach { insuredPersonAppendixCsvLine ->
            // 利用者補足の１行に対応する７票の行を抽出
            val targetServicePlanAppendixes = servicePlanCsvAppendixLines.stream().filter {
                it.isCorrespond(insuredPersonAppendixCsvLine)
            }.toList()
            servicePlanAppendixes.add(create(insuredPersonAppendixCsvLine, targetServicePlanAppendixes))
        }
        return servicePlanAppendixes
    }

    private fun create(
        insuredPersonAppendixCsvLine: InsuredPersonAppendixCsvLine,
        servicePlanAppendixCsvLines: List<ServicePlanAppendixCsvLine>
    ): ServicePlanAppendix {
        val insuredPerson = InsuredPerson(
            insuredPersonAppendixCsvLine.name,
            insuredPersonAppendixCsvLine.nameKana,
            Sex.fromDivision(insuredPersonAppendixCsvLine.sex),
            JapaneseDate(LocalDate.parse(insuredPersonAppendixCsvLine.birth, DateTimeFormatter.ofPattern("yyyyMMdd"))),
            InsureLicense(
                insuredPersonAppendixCsvLine.insuredNumber,
                CareLevel.fromDivision(insuredPersonAppendixCsvLine.careLevel),
                "要介護1", // TODO どこの項目を入れるか確認
                LocalDate.of(2019, 11, 30), // TODO どこの項目を入れるか確認
                insuredPersonAppendixCsvLine.creditLimit.toInt(),
                LocalDate.parse(
                    insuredPersonAppendixCsvLine.creditLimitApplyStartDate, DateTimeFormatter.ofPattern("yyyyMMdd")
                ),
                LocalDate.parse(
                    insuredPersonAppendixCsvLine.creditLimitApplyEndDate, DateTimeFormatter.ofPattern("yyyyMMdd")
                )
            )
        )

        val time = ProvidedTime.Time(
            LocalTime.of(10, 0),
            LocalTime.of(12, 0)
        )

        val providedServices = mutableListOf<ProvidedService>()
        servicePlanAppendixCsvLines.forEach {servicePlanAppendixCsvLine ->
            providedServices.add(
                ProvidedService(
                    CareService(
                        "通所介護１", // TODO どこの項目を入れるか確認
                        servicePlanAppendixCsvLine.serviceCode,
                        servicePlanAppendixCsvLine.unitNumber.toInt(),
                        servicePlanAppendixCsvLine.discountRate.toInt(),
                        servicePlanAppendixCsvLine.discountedUnitNumber.toInt(),
                        servicePlanAppendixCsvLine.totalUnitNumber.toInt(),
                        servicePlanAppendixCsvLine.overKindUnitNumber.toInt(),
                        servicePlanAppendixCsvLine.kindUnitNumber.toInt(),
                        servicePlanAppendixCsvLine.overDivisionUnitNumber.toInt(),
                        servicePlanAppendixCsvLine.divisionUnitNumber.toInt(),
                        servicePlanAppendixCsvLine.unitPrice,
                        servicePlanAppendixCsvLine.totalInsuranceDemand.toInt(),
                        servicePlanAppendixCsvLine.benefitsRate.toInt(),
                        servicePlanAppendixCsvLine.paidInsuranceDemandFromInsurance.toInt(),
                        servicePlanAppendixCsvLine.paidInsuranceDemandFromInsuredPerson.toInt(),
                        servicePlanAppendixCsvLine.totalInsuredPersonDemand.toInt()
                    ),
                    Office("とある通所介護事業所", servicePlanAppendixCsvLine.serviceOfficeCode, "03-1234-5678"),
                    // TODO サービス事業所名は6票が必要, サービス事業所の電話番号はどこから取得するか
                    ProvidedTime() // TODO 提供日は6票が必要
                        .addPlan(
                            ProvidedTime.Time(
                                LocalTime.of(10, 0),
                                LocalTime.of(12, 0)
                            )
                            , 1
                        )
                        .addPlan(time, 3)
                        .addPlan(time, 5)
                        .addResult(time, 1)
                        .addResult(time, 5)
                )
            )
        }

        return ServicePlanAppendix(
            // TODO 仮実装
            ServiceProvisionYearMonth(
                YearMonth.parse(
                    insuredPersonAppendixCsvLine.serviceYearMonth,
                    DateTimeFormatter.ofPattern("yyyyMM")
                )
            ),
            LocalDate.parse(
                servicePlanAppendixCsvLines.get(0).creationDate,
                DateTimeFormatter.ofPattern("yyyyMMdd")
            ),
            insuredPerson,
            ProvidedServices(providedServices),
            insuredPersonAppendixCsvLine.creditLimit.toInt(),
            TotalUnitNumber(1, 2, 3, 4, 5, 6, 7, 8, 9),
            // TODO TotalUnitNumberは情報が無い
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