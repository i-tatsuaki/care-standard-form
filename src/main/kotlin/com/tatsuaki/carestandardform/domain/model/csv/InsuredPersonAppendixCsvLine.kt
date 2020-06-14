package com.tatsuaki.carestandardform.domain.model.csv

class InsuredPersonAppendixCsvLine (
    csvLine: Array<String>
) {
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

    // 訪問
    val creditLimitVisitCare: String
    val totalUnitVisitCare: String
    val unitOverCreditLimitVisitCare: String

    // 訪問入浴
    val creditLimitVisitCareBathing: String
    val totalUnitVisitCareBathing: String
    val unitOverCreditLimitVisitCareBathing: String

    // 訪問看護
    val creditLimitVisitNursing: String
    val totalUnitVisitNursing: String
    val unitOverCreditLimitVisitNursing: String

    // 訪問リハビリ
    val creditLimitVisitRehabilitation: String
    val totalUnitVisitRehabilitation: String
    val unitOverCreditLimitVisitRehabilitation: String

    // 通所介護
    val creditLimitDayCare: String
    val totalUnitDayCare: String
    val unitOverCreditLimitDayCare: String

    // 通所リハビリ
    val creditLimitDayCareRehabilitation: String
    val totalUnitDayCareRehabilitation: String
    val unitOverCreditLimitDayCareRehabilitation: String

    // 福祉用具貸与
    val creditLimitWelfareEquipment: String
    val totalUnitWelfareEquipment: String
    val unitOverCreditLimitWelfareEquipment: String

    // 入所生活介護
    val creditLimitStayLifeCare: String
    val totalUnitStayLifeCare: String
    val unitOverCreditLimitStayLifeCare: String

    // 入所療養介護
    val creditLimitStayMedicalCare: String
    val totalUnitStayMedicalCare: String
    val unitOverCreditLimitStayMedicalCare: String

    // 夜間対応型訪問介護
    val creditLimitNightlyVisitCare: String
    val totalUnitNightlyVisitCare: String
    val unitOverCreditLimitNightlyVisitCare: String

    // 認知症対応型通所介護
    val creditLimitDementiaDayCare: String
    val totalUnitDementiaDayCare: String
    val unitOverCreditLimitDementiaDayCare: String

    // 認知症対応型共同生活介護
    val creditLimitDementiaLifeCare: String
    val totalUnitDementiaLifeCare: String
    val unitOverCreditLimitDementiaLifeCare: String

    // 地域密着型通所介護
    val creditLimitCommunityVisitCare: String
    val totalUnitCommunityVisitCare: String
    val unitOverCreditLimitCommunityVisitCare: String

    val totalUnitOverCreditLimit: String
    val stayOverUnitCreditLimit: String
    val stayDaysPreviousMonth: String
    val stayAccumlationDays: String
    val previousCareLevel: String
    val agreementOffice: String
    val registrationDate: String
    val timeStamp: String
    val updatedOffice: String
    val servicePlanCreatedDate: String
    val servicePlanAppendixCreatedDate: String
    val servicePlanNotificationDate: String
    val identification: String
    val targetDate: String

    init {

        insureNumber = csvLine[0]
        insuredNumber = csvLine[1]
        carePlanCreationDate = csvLine[2]
        nameKana = csvLine[3]
        name = csvLine[4]
        sex = csvLine[5]
        birth = csvLine[6]
        postalCode = csvLine[7]
        address1 = csvLine[8]
        address2 = csvLine[9]
        phoneNumber = csvLine[10]
        certifiedDate = csvLine[11]
        creditLimitApplyStartDate = csvLine[12]
        creditLimitApplyEndDate = csvLine[13]
        certifiedStatus = csvLine[14]
        careLevelChangedDate = csvLine[15]
        careLevel = csvLine[16]
        creditLimit = csvLine[17]
        creditLimitVisitCare = csvLine[18]
        totalUnitVisitCare = csvLine[19]
        unitOverCreditLimitVisitCare = csvLine[20]
        creditLimitVisitCareBathing = csvLine[21]
        totalUnitVisitCareBathing = csvLine[22]
        unitOverCreditLimitVisitCareBathing = csvLine[23]
        creditLimitVisitNursing = csvLine[24]
        totalUnitVisitNursing = csvLine[25]
        unitOverCreditLimitVisitNursing = csvLine[26]
        creditLimitVisitRehabilitation = csvLine[27]
        totalUnitVisitRehabilitation = csvLine[28]
        unitOverCreditLimitVisitRehabilitation = csvLine[29]
        creditLimitDayCare = csvLine[30]
        totalUnitDayCare = csvLine[31]
        unitOverCreditLimitDayCare = csvLine[32]
        creditLimitDayCareRehabilitation = csvLine[33]
        totalUnitDayCareRehabilitation = csvLine[34]
        unitOverCreditLimitDayCareRehabilitation = csvLine[35]
        creditLimitWelfareEquipment = csvLine[36]
        totalUnitWelfareEquipment = csvLine[37]
        unitOverCreditLimitWelfareEquipment = csvLine[38]
        creditLimitStayLifeCare = csvLine[39]
        totalUnitStayLifeCare = csvLine[40]
        unitOverCreditLimitStayLifeCare = csvLine[41]
        creditLimitStayMedicalCare = csvLine[42]
        totalUnitStayMedicalCare = csvLine[43]
        unitOverCreditLimitStayMedicalCare = csvLine[44]
        creditLimitNightlyVisitCare = csvLine[45]
        totalUnitNightlyVisitCare = csvLine[46]
        unitOverCreditLimitNightlyVisitCare = csvLine[47]
        creditLimitDementiaDayCare = csvLine[48]
        totalUnitDementiaDayCare = csvLine[49]
        unitOverCreditLimitDementiaDayCare = csvLine[50]
        creditLimitDementiaLifeCare = csvLine[51]
        totalUnitDementiaLifeCare = csvLine[52]
        unitOverCreditLimitDementiaLifeCare = csvLine[53]
        creditLimitCommunityVisitCare = csvLine[54]
        totalUnitCommunityVisitCare = csvLine[55]
        unitOverCreditLimitCommunityVisitCare = csvLine[56]
        totalUnitOverCreditLimit = csvLine[57]
        stayOverUnitCreditLimit = csvLine[58]
        stayDaysPreviousMonth = csvLine[59]
        stayAccumlationDays = csvLine[60]
        previousCareLevel = csvLine[61]
        agreementOffice = csvLine[62]
        registrationDate = csvLine[63]
        timeStamp = csvLine[64]
        updatedOffice = csvLine[65]
        servicePlanCreatedDate = csvLine[66]
        servicePlanAppendixCreatedDate = csvLine[67]
        servicePlanNotificationDate = csvLine[68]
        identification = csvLine[69]
        targetDate = csvLine[70]
    }

}