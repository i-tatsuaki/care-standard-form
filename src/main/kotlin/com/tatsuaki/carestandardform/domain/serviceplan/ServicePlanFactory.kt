package com.tatsuaki.carestandardform.domain.serviceplan

import com.tatsuaki.carestandardform.domain.ServiceProvisionYearMonth
import com.tatsuaki.carestandardform.domain.model.*
import com.tatsuaki.carestandardform.domain.model.csv.InsuredPersonAppendixCsvLine
import com.tatsuaki.carestandardform.domain.model.csv.ServicePlanAppendixCsvLine
import com.tatsuaki.carestandardform.domain.model.csv.ServicePlanCsvLine
import com.tatsuaki.carestandardform.util.JapaneseDate
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
        servicePlanLines: ArrayList<ServicePlanCsvLine>,
        servicePlanCsvAppendixLines: ArrayList<ServicePlanAppendixCsvLine>
    ): List<ServicePlan> {
        val servicePlans: ArrayList<ServicePlan> = arrayListOf()
        insuredPersonAppendixCsvLines.stream().forEach { insuredPersonAppendixCsvLine ->
            // 利用者補足の１行に対応する６票の行を抽出
            val targetServicePlans = servicePlanLines.stream().filter {
                it.isCorrespond(insuredPersonAppendixCsvLine)
            }.toList()
            val targetServicePlanAppendixes = servicePlanCsvAppendixLines.stream().filter {
                it.isCorrespond(insuredPersonAppendixCsvLine)
            }.toList()
            servicePlans.add(create(insuredPersonAppendixCsvLine, targetServicePlans, targetServicePlanAppendixes))
        }
        return servicePlans
    }

    private fun create(
        insuredPersonAppendixCsvLine: InsuredPersonAppendixCsvLine,
        servicePlanCsvLines: List<ServicePlanCsvLine>,
        servicePlanAppendixCsvLines: List<ServicePlanAppendixCsvLine>
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
        serviceProvidedMap.forEach {servicePlanCsvLineMap ->
            val servicePlanAppendixCsvLine = servicePlanAppendixCsvLines.first { it.getProvidedServiceKey().equals(servicePlanCsvLineMap.key) }
            val providedTime = ProvidedTime()
            servicePlanCsvLineMap.value.stream().forEach { eachDay ->
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
                    Office(servicePlanCsvLineMap.value.get(0).serviceOfficeName, servicePlanCsvLineMap.value.get(0).serviceOfficeCode, "03-1234-5678"),
                    // TODO サービス事業所の電話番号はどこから取得するか
                    providedTime
                )
            )
        }

        val kindUnitNumberManagements = mutableListOf<KindUnitNumberManagement>()
        kindUnitNumberManagements.add(KindUnitNumberManagement(insuredPersonAppendixCsvLine.serviceKindCode1, insuredPersonAppendixCsvLine.creditLimit1, insuredPersonAppendixCsvLine.totalUnit1, insuredPersonAppendixCsvLine.unitOverCreditLimit1))
        kindUnitNumberManagements.add(KindUnitNumberManagement(insuredPersonAppendixCsvLine.serviceKindCode2, insuredPersonAppendixCsvLine.creditLimit2, insuredPersonAppendixCsvLine.totalUnit2, insuredPersonAppendixCsvLine.unitOverCreditLimit2))
        kindUnitNumberManagements.add(KindUnitNumberManagement(insuredPersonAppendixCsvLine.serviceKindCode3, insuredPersonAppendixCsvLine.creditLimit3, insuredPersonAppendixCsvLine.totalUnit3, insuredPersonAppendixCsvLine.unitOverCreditLimit3))
        kindUnitNumberManagements.add(KindUnitNumberManagement(insuredPersonAppendixCsvLine.serviceKindCode4, insuredPersonAppendixCsvLine.creditLimit4, insuredPersonAppendixCsvLine.totalUnit4, insuredPersonAppendixCsvLine.unitOverCreditLimit4))
        kindUnitNumberManagements.add(KindUnitNumberManagement(insuredPersonAppendixCsvLine.serviceKindCode5, insuredPersonAppendixCsvLine.creditLimit5, insuredPersonAppendixCsvLine.totalUnit5, insuredPersonAppendixCsvLine.unitOverCreditLimit5))
        kindUnitNumberManagements.add(KindUnitNumberManagement(insuredPersonAppendixCsvLine.serviceKindCode6, insuredPersonAppendixCsvLine.creditLimit6, insuredPersonAppendixCsvLine.totalUnit6, insuredPersonAppendixCsvLine.unitOverCreditLimit6))
        kindUnitNumberManagements.add(KindUnitNumberManagement(insuredPersonAppendixCsvLine.serviceKindCode7, insuredPersonAppendixCsvLine.creditLimit7, insuredPersonAppendixCsvLine.totalUnit7, insuredPersonAppendixCsvLine.unitOverCreditLimit7))
        kindUnitNumberManagements.add(KindUnitNumberManagement(insuredPersonAppendixCsvLine.serviceKindCode8, insuredPersonAppendixCsvLine.creditLimit8, insuredPersonAppendixCsvLine.totalUnit8, insuredPersonAppendixCsvLine.unitOverCreditLimit8))
        kindUnitNumberManagements.add(KindUnitNumberManagement(insuredPersonAppendixCsvLine.serviceKindCode9, insuredPersonAppendixCsvLine.creditLimit9, insuredPersonAppendixCsvLine.totalUnit9, insuredPersonAppendixCsvLine.unitOverCreditLimit9))

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
            ProvidedServices(providedServices),
            insuredPersonAppendixCsvLine.creditLimit.toInt(),
            TotalUnitNumber(1, 2, 3, 4, 5, 6, 7, 8, 9),
            // TODO TotalUnitNumberは情報が無い
            kindUnitNumberManagements,
            TotalUnitNumberManagement(46, 47, 48),
            UserCountShortStay(5, 10, 15)
            // TODO UserCountShortStayはどこの情報を取るか要検討
        )
    }
}
