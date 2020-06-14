package com.tatsuaki.carestandardform.domain.model.csv

class InsuredPersonAppendixCsvLine (
    csvLine: Array<String>
) {
    val csvVersion: String
    val insureNumber: String
    val insuredNumber: String
    val carePlanCreationDate: String
    val nameKana: String
    val name: String
    val sex: String
    val birth: String
    val postalCode: String
    val address1: String
    val address2: String
    val phoneNumber: String
    val certifiedDate: String
    val creditLimitApplyStartDate: String
    val creditLimitApplyEndDate: String
    val certifiedStatus: String
    val careLevelChangedDate: String
    val careLevel: String
    val creditLimit: String

    val serviceDivision1: String
    val creditLimit1: String
    val totalUnit1: String
    val unitOverCreditLimit1: String

    val serviceDivision2: String
    val creditLimit2: String
    val totalUnit2: String
    val unitOverCreditLimit2: String

    val serviceDivision3: String
    val creditLimit3: String
    val totalUnit3: String
    val unitOverCreditLimit3: String

    val serviceDivision4: String
    val creditLimit4: String
    val totalUnit4: String
    val unitOverCreditLimit4: String

    val serviceDivision5: String
    val creditLimit5: String
    val totalUnit5: String
    val unitOverCreditLimit5: String

    val serviceDivision6: String
    val creditLimit6: String
    val totalUnit6: String
    val unitOverCreditLimit6: String

    val serviceDivision7: String
    val creditLimit7: String
    val totalUnit7: String
    val unitOverCreditLimit7: String

    val serviceDivision8: String
    val creditLimit8: String
    val totalUnit8: String
    val unitOverCreditLimit8: String

    val serviceDivision9: String
    val creditLimit9: String
    val totalUnit9: String
    val unitOverCreditLimit9: String

    val totalUnitOverCreditLimit: String
    val stayDaysPreviousMonth: String
    val stayAccumulationDays: String
    val previousCareLevel: String
    val agreementOffice: String
    val registrationDate: String
    val updatedOffice: String
    val identification: String
    val serviceYearMonth: String

    init {
        csvVersion = csvLine[0]
        insureNumber = csvLine[1]
        insuredNumber = csvLine[2]
        carePlanCreationDate = csvLine[3]
        nameKana = csvLine[4]
        name = csvLine[5]
        sex = csvLine[6]
        birth = csvLine[7]
        postalCode = csvLine[8]
        address1 = csvLine[9]
        address2 = csvLine[10]
        phoneNumber = csvLine[11]
        certifiedDate = csvLine[12]
        creditLimitApplyStartDate = csvLine[13]
        creditLimitApplyEndDate = csvLine[14]
        certifiedStatus = csvLine[15]
        careLevelChangedDate = csvLine[16]
        careLevel = csvLine[17]
        creditLimit = csvLine[18]

        serviceDivision1 = csvLine[19]
        creditLimit1 = csvLine[20]
        totalUnit1 = csvLine[21]
        unitOverCreditLimit1 = csvLine[22]

        serviceDivision2 = csvLine[23]
        creditLimit2 = csvLine[24]
        totalUnit2 = csvLine[25]
        unitOverCreditLimit2 = csvLine[26]

        serviceDivision3 = csvLine[27]
        creditLimit3 = csvLine[28]
        totalUnit3 = csvLine[29]
        unitOverCreditLimit3 = csvLine[30]

        serviceDivision4 = csvLine[31]
        creditLimit4 = csvLine[32]
        totalUnit4 = csvLine[33]
        unitOverCreditLimit4 = csvLine[34]

        serviceDivision5 = csvLine[35]
        creditLimit5 = csvLine[36]
        totalUnit5 = csvLine[37]
        unitOverCreditLimit5 = csvLine[38]

        serviceDivision6 = csvLine[39]
        creditLimit6 = csvLine[40]
        totalUnit6 = csvLine[41]
        unitOverCreditLimit6 = csvLine[42]

        serviceDivision7 = csvLine[43]
        creditLimit7 = csvLine[44]
        totalUnit7 = csvLine[45]
        unitOverCreditLimit7 = csvLine[46]

        serviceDivision8 = csvLine[47]
        creditLimit8 = csvLine[48]
        totalUnit8 = csvLine[49]
        unitOverCreditLimit8 = csvLine[50]

        serviceDivision9 = csvLine[51]
        creditLimit9 = csvLine[52]
        totalUnit9 = csvLine[53]
        unitOverCreditLimit9 = csvLine[54]
        totalUnitOverCreditLimit = csvLine[55]
        stayDaysPreviousMonth = csvLine[56]
        stayAccumulationDays = csvLine[57]
        previousCareLevel = csvLine[58]
        agreementOffice = csvLine[59]
        registrationDate = csvLine[60]
        updatedOffice = csvLine[61]
        identification = csvLine[62]
        serviceYearMonth = csvLine[63]
    }

}