package com.tatsuaki.carestandardform.domain.model.csv

class ServicePlanAppendixCsvLine (
    csvLine: Array<String>
) {
    val csvVersion: String
    val insureNumber: String
    val insuredNumber: String
    val creationDate: String
    val serviceYearMonth: String
    val creationOfficeName: String
    val serviceOfficeCode: String
    val numberOfSatellite: String
    val serviceCode: String
    val unitNumber: String
    val discountRate: String
    val discountedUnitNumber: String
    val totalCount: String
    val totalUnitNumber: String
    val overKindUnitNumber: String
    val kindUnitNumber: String
    val overDivisionUnitNumber: String
    val divisionUnitNumber: String
    val unitPrice: String
    val totalInsuranceDemand: String
    val benefitsRate: String
    val paidInsuranceDemandFromInsurance: String
    val demandByFixedDemandService: String
    val paidInsuranceDemandFromInsuredPerson: String
    val totalInsuredPersonDemand: String
    val useCountShortStayPreviousMonth: String
    val useCountShortStayThisMonth: String
    val accumulateUseCountShortStay: String
    val isOverThirty: String
    val dummyOfficeCode: String
    val inputOfficeCode: String
    val registrationDate: String
    val updateOfficeCode: String
    val identification: String

    init {
        csvVersion = csvLine[0]
        insureNumber = csvLine[1]
        insuredNumber = csvLine[2]
        creationDate = csvLine[3]
        serviceYearMonth = csvLine[4]
        creationOfficeName = csvLine[5]
        serviceOfficeCode = csvLine[6]
        numberOfSatellite = csvLine[7]
        serviceCode = csvLine[8]
        unitNumber = csvLine[9]
        discountRate = csvLine[10]
        discountedUnitNumber = csvLine[11]
        totalCount = csvLine[12]
        totalUnitNumber = csvLine[13]
        overKindUnitNumber = csvLine[14]
        kindUnitNumber = csvLine[15]
        overDivisionUnitNumber = csvLine[16]
        divisionUnitNumber = csvLine[17]
        unitPrice = csvLine[18]
        totalInsuranceDemand = csvLine[19]
        benefitsRate = csvLine[20]
        paidInsuranceDemandFromInsurance = csvLine[21]
        demandByFixedDemandService = csvLine[22]
        paidInsuranceDemandFromInsuredPerson = csvLine[23]
        totalInsuredPersonDemand = csvLine[24]
        useCountShortStayPreviousMonth = csvLine[25]
        useCountShortStayThisMonth = csvLine[26]
        accumulateUseCountShortStay = csvLine[27]
        isOverThirty = csvLine[28]
        dummyOfficeCode = csvLine[29]
        inputOfficeCode = csvLine[30]
        registrationDate = csvLine[31]
        updateOfficeCode = csvLine[32]
        identification = csvLine[33]
    }

    fun isCorrespond(insuredPersonAppendixCsvLine: InsuredPersonAppendixCsvLine) : Boolean {
        return this.csvVersion.equals(insuredPersonAppendixCsvLine.csvVersion)
                && this.insureNumber.equals(insuredPersonAppendixCsvLine.insureNumber)
                && this.insuredNumber.equals(insuredPersonAppendixCsvLine.insuredNumber)
                && this.serviceYearMonth.equals(insuredPersonAppendixCsvLine.serviceYearMonth)
    }

}