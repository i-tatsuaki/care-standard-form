package com.tatsuaki.carestandardform.domain.serviceplan

import com.tatsuaki.carestandardform.domain.ServiceProvisionYearMonth
import com.tatsuaki.carestandardform.domain.model.*
import com.tatsuaki.carestandardform.domain.model.csv.InsuredPersonAppendixCsvLine
import com.tatsuaki.carestandardform.domain.model.csv.ServicePlanAppendixCsvLine
import com.tatsuaki.carestandardform.domain.model.csv.ServicePlanCsvLine
import com.tatsuaki.carestandardform.util.JapaneseDate
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.stream.Collectors.groupingBy
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
                if (insuredPersonAppendixCsvLine.careLevel.equals("")) null else CareLevel.fromDivision(insuredPersonAppendixCsvLine.careLevel),
                if (insuredPersonAppendixCsvLine.previousCareLevel.equals("")) null else CareLevel.fromDivision(insuredPersonAppendixCsvLine.previousCareLevel),
                if (insuredPersonAppendixCsvLine.careLevelChangedDate.equals("")) null else LocalDate.parse(
                    insuredPersonAppendixCsvLine.careLevelChangedDate, DateTimeFormatter.ofPattern("yyyyMMdd"))
                ,
                if (insuredPersonAppendixCsvLine.previousCareLevel.equals("")) null else insuredPersonAppendixCsvLine.creditLimit.toInt(),
                if (insuredPersonAppendixCsvLine.creditLimitApplyStartDate.equals("")) null else LocalDate.parse(
                    insuredPersonAppendixCsvLine.creditLimitApplyStartDate, DateTimeFormatter.ofPattern("yyyyMMdd")
                ),
                if (insuredPersonAppendixCsvLine.creditLimitApplyEndDate.equals("")) null else LocalDate.parse(
                    insuredPersonAppendixCsvLine.creditLimitApplyEndDate, DateTimeFormatter.ofPattern("yyyyMMdd")
                )
            )
        )

        val serviceProvidedMap = servicePlanCsvLines.stream()
            .collect(
                groupingBy(ServicePlanCsvLine::getProvidedServiceKey)
            ).toSortedMap()

        val providedServices = arrayListOf<ProvidedService>()
        serviceProvidedMap.forEach {servicePlanCsvLineMap ->
            val servicePlanAppendixCsvLine = servicePlanAppendixCsvLines.first { it.getProvidedServiceKey().equals(servicePlanCsvLineMap.key) }
            val providedTime = ProvidedTime()
            servicePlanCsvLineMap.value.stream().forEach { eachDay ->
                providedTime.addPlan(
                    ProvidedTime.Time(
                        eachDay.serviceStartTime.substring(0, 4),
                        eachDay.serviceEndTime.substring(0, 4)
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
                        if (servicePlanAppendixCsvLine.discountRate.equals("")) null else servicePlanAppendixCsvLine.discountRate.toInt(),
                        if (servicePlanAppendixCsvLine.discountedUnitNumber.equals("")) null else servicePlanAppendixCsvLine.discountedUnitNumber.toInt(),
                        if (servicePlanAppendixCsvLine.totalUnitNumber.equals("")) null else servicePlanAppendixCsvLine.totalUnitNumber.toInt(),
                        if (servicePlanAppendixCsvLine.overKindUnitNumber.equals("")) null else servicePlanAppendixCsvLine.overKindUnitNumber.toInt(),
                        if (servicePlanAppendixCsvLine.kindUnitNumber.equals("")) null else servicePlanAppendixCsvLine.kindUnitNumber.toInt(),
                        if (servicePlanAppendixCsvLine.overDivisionUnitNumber.equals("")) null else servicePlanAppendixCsvLine.overDivisionUnitNumber.toInt(),
                        if (servicePlanAppendixCsvLine.divisionUnitNumber.equals("")) null else servicePlanAppendixCsvLine.divisionUnitNumber.toInt(),
                        servicePlanAppendixCsvLine.unitPrice,
                        if (servicePlanAppendixCsvLine.totalInsuranceDemand.equals("")) null else servicePlanAppendixCsvLine.totalInsuranceDemand.toInt(),
                        if (servicePlanAppendixCsvLine.benefitsRate.equals("")) null else servicePlanAppendixCsvLine.benefitsRate.toInt(),
                        if (servicePlanAppendixCsvLine.paidInsuranceDemandFromInsurance.equals("")) null else servicePlanAppendixCsvLine.paidInsuranceDemandFromInsurance.toInt(),
                        if (servicePlanAppendixCsvLine.paidInsuranceDemandFromInsuredPerson.equals("")) null else servicePlanAppendixCsvLine.paidInsuranceDemandFromInsuredPerson.toInt(),
                        if (servicePlanAppendixCsvLine.totalInsuredPersonDemand.equals("")) null else servicePlanAppendixCsvLine.totalInsuredPersonDemand.toInt()
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
                    servicePlanAppendixCsvLines[0].serviceOfficeName,
                    insuredPersonAppendixCsvLine.agreementOffice,
                    "") // 居宅介護支援事業所の電話番号はcsvから取得できないため空白とする
            ),
            insuredPerson,
            "", // 届出年月日はcsvに含まれていないため空白とする
            if (insuredPersonAppendixCsvLine.stayDaysPreviousMonth.equals("")) null else insuredPersonAppendixCsvLine.stayDaysPreviousMonth.toInt(),
            ProvidedServices(providedServices),
            if (insuredPersonAppendixCsvLine.creditLimit.equals("")) null else insuredPersonAppendixCsvLine.creditLimit.toInt(),
            TotalUnitNumber("", "", "", "", "", "", "", "", ""),
            // 単位数合計情報はcsvに含まれていないため空白とする
            kindUnitNumberManagements,
            UserCountShortStay(
                if (servicePlanAppendixCsvLines[0].useCountShortStayPreviousMonth.equals("")) null else servicePlanAppendixCsvLines[0].useCountShortStayPreviousMonth.toInt(),
                if (servicePlanAppendixCsvLines[0].useCountShortStayThisMonth.equals("")) null else servicePlanAppendixCsvLines[0].useCountShortStayThisMonth.toInt(),
                if (servicePlanAppendixCsvLines[0].accumulateUseCountShortStay.equals("")) null else servicePlanAppendixCsvLines[0].accumulateUseCountShortStay.toInt()
            )
        )
    }
}
