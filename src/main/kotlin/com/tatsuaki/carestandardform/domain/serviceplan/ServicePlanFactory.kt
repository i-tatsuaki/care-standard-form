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
                CareLevel.fromDivision(insuredPersonAppendixCsvLine.previousCareLevel),
                LocalDate.parse(
                    insuredPersonAppendixCsvLine.careLevelChangedDate, DateTimeFormatter.ofPattern("yyyyMMdd"))
                ,
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
                        "(${servicePlanAppendixCsvLine.serviceCode})", // サービス名はcsvに含まれないため、サービスコードで代替する
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
                    Office(
                        servicePlanCsvLineMap.value.get(0).serviceOfficeName,
                        servicePlanCsvLineMap.value.get(0).serviceOfficeCode,
                        "" // サービス事業所の電話番号はcsvに含まれていないため空白とする
                    ),
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
            Insure(insuredPersonAppendixCsvLine.insureNumber, "(${insuredPersonAppendixCsvLine.insureNumber})"), // 保険者名はcsvから取得できないため保険者番号を代替とする
            CareManager(
                servicePlanCsvLines.get(0).creationPersonName,
                Office(
                    servicePlanAppendixCsvLines[0].creationOfficeName,
                    insuredPersonAppendixCsvLine.agreementOffice,
                    "") // 居宅介護支援事業所の電話番号はcsvから取得できないため空白とする
            ),
            insuredPerson,
            "", // 届出年月日はcsvに含まれていないため空白とする
            insuredPersonAppendixCsvLine.stayDaysPreviousMonth.toInt(),
            ProvidedServices(providedServices),
            insuredPersonAppendixCsvLine.creditLimit.toInt(),
            TotalUnitNumber("", "", "", "", "", "", "", "", ""),
            // 単位数合計情報はcsvに含まれていないため空白とする
            kindUnitNumberManagements,
            UserCountShortStay(
                servicePlanAppendixCsvLines[0].useCountShortStayPreviousMonth.toInt(),
                servicePlanAppendixCsvLines[0].useCountShortStayThisMonth.toInt(),
                servicePlanAppendixCsvLines[0].accumulateUseCountShortStay.toInt()
            )
        )
    }
}
