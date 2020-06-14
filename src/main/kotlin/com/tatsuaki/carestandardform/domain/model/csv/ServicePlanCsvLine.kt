package com.tatsuaki.carestandardform.domain.model.csv

class ServicePlanCsvLine (
    csvLine: Array<String>
) {
    val csvVersion: String
    val insureNumber: String
    val insuredNumber: String
    val creationDate: String
    val serviceYearMonth: String
    val creationOfficeCode: String
    val creationPersonName: String
    val unitNumber: String
    val shortStayDaysByPreviousMonth: String
    val serviceDate: String
    val proratedDays: String
    val serviceStartTime: String
    val serviceEndTime: String
    val servideCount: String
    val serviceCode: String
    val serviceIdentification: String
    val serviceOfficeCode: String
    val serviceOfficeName: String
    val numberOfSatellite: String
    val isOverThirty: String
    val updateOfficeCode: String
    val identification: String

    init {
        csvVersion = csvLine[0]
        insureNumber = csvLine[1]
        insuredNumber = csvLine[2]
        creationDate = csvLine[3]
        serviceYearMonth = csvLine[4]
        creationOfficeCode = csvLine[5]
        creationPersonName = csvLine[6]
        unitNumber = csvLine[7]
        shortStayDaysByPreviousMonth = csvLine[8]
        serviceDate = csvLine[9]
        proratedDays = csvLine[10]
        serviceStartTime = csvLine[11]
        serviceEndTime = csvLine[12]
        servideCount = csvLine[13]
        serviceCode = csvLine[14]
        serviceIdentification = csvLine[15]
        serviceOfficeCode = csvLine[16]
        serviceOfficeName = csvLine[17]
        numberOfSatellite = csvLine[18]
        isOverThirty = csvLine[19]
        updateOfficeCode = csvLine[20]
        identification = csvLine[21]
    }
}