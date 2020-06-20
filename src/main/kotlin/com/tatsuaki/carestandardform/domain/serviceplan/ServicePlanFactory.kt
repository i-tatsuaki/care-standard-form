package com.tatsuaki.carestandardform.domain.serviceplan

import com.tatsuaki.carestandardform.domain.ServiceProvisionYearMonth
import com.tatsuaki.carestandardform.domain.model.*
import com.tatsuaki.carestandardform.domain.model.csv.InsuredPersonAppendixCsvLine
import com.tatsuaki.carestandardform.domain.model.csv.ServicePlanCsvLine
import com.tatsuaki.carestandardform.util.JapaneseDate
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalTime
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.stream.Collectors
import kotlin.streams.toList

class ServicePlanFactory {
    fun createServicePlans(
        insuredPersonAppendixCsvLines: ArrayList<InsuredPersonAppendixCsvLine>,
        servicePlanLines: ArrayList<ServicePlanCsvLine>
    ): List<ServicePlan> {
        val servicePlans: ArrayList<ServicePlan> = arrayListOf()
        insuredPersonAppendixCsvLines.stream().forEach { insuredPersonAppendixCsvLine ->
            // 利用者補足の１行に対応する６票の行を抽出
            val targetServicePlans = servicePlanLines.stream().filter {
                it.isCorrespond(insuredPersonAppendixCsvLine)
            }.toList()
            servicePlans.add(create(insuredPersonAppendixCsvLine, targetServicePlans))
        }
        return servicePlans
    }

    private fun create(
        insuredPersonAppendixCsvLine: InsuredPersonAppendixCsvLine,
        servicePlanCsvLines: List<ServicePlanCsvLine>
    ): ServicePlan {
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

        val serviceProvidedMap = servicePlanCsvLines.stream().collect(
            Collectors.groupingBy(ServicePlanCsvLine::getProvidedServiceKey)
        )

        val providedServices = arrayListOf<ProvidedService>()
        serviceProvidedMap.forEach {

            val providedTime = ProvidedTime()
            it.value.stream().forEach { eachDay ->
                providedTime.addPlan(
                    ProvidedTime.Time(
                        LocalTime.of(
                            eachDay.serviceStartTime.substring(0, 2).toInt(),
                            eachDay.serviceStartTime.substring(2, 4).toInt()
                        ),
                        LocalTime.of(
                            eachDay.serviceEndTime.substring(0, 2).toInt(),
                            eachDay.serviceEndTime.substring(2, 4).toInt()
                        )
                    ),
                    LocalDate.parse(eachDay.serviceDate, DateTimeFormatter.ofPattern("yyyyMMdd")).dayOfMonth
                )
            }

            providedServices.add(
                ProvidedService(
                    CareService(
                        "通所介護１", // TODO どこから取得するか（masterが必要そう）
                        it.value.get(0).serviceCode,
                        it.value.get(0).unitNumber.toInt(),
                        90, // TODO 7票が必要
                        119, // TODO 7票が必要
                        488, // TODO 7票が必要
                        30, // TODO 7票が必要
                        458, // TODO 7票が必要
                        58, // TODO 7票が必要
                        400, // TODO 7票が必要
                        BigDecimal(10.0), // TODO 7票が必要
                        4880, // TODO 7票が必要
                        90, // TODO 7票が必要
                        4392, // TODO 7票が必要
                        488, // TODO 7票が必要
                        488 // TODO 7票が必要
                    ),
                    Office(it.value.get(0).serviceOfficeName, it.value.get(0).serviceOfficeCode, "03-1234-5678"),
                    // TODO サービス事業所の電話番号はどこから取得するか
                    providedTime
                )
            )
        }

        return ServicePlan(
            ServiceProvisionYearMonth(
                YearMonth.parse(
                    insuredPersonAppendixCsvLine.serviceYearMonth,
                    DateTimeFormatter.ofPattern("yyyyMM")
                )
            ),
            LocalDate.parse(
                servicePlanCsvLines.get(0).creationDate,
                DateTimeFormatter.ofPattern("yyyyMMdd")
            ),
            Insure(insuredPersonAppendixCsvLine.insureNumber, ""), // TODO 保険者名をどこから取得するか
            CareManager(
                servicePlanCsvLines.get(0).creationPersonName,
                Office("", insuredPersonAppendixCsvLine.agreementOffice, "")
            ), // TODO ケアマネ事業所名、ケアマネ電話番号はどこから取得するか
            insuredPerson,
            LocalDate.of(2019, 12, 30), // TODO どこの項目を入れるか確認
            insuredPersonAppendixCsvLine.stayDaysPreviousMonth.toInt(),
            providedServices
        )
    }
}
