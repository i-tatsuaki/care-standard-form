package com.tatsuaki.carestandardform.domain.model.csv

import org.junit.Test
import java.util.Arrays.asList
import kotlin.test.assertEquals

class InsuredPersonAppendixCsvLineTest {

    @Test
    fun getMethodTest() {
        // given
        val insuredPersonAppendixCsvLine =
            InsuredPersonAppendixCsvLine(
                arrayListOf<String>(
                    "123456",
                    "H123456789",
                    "20200101",
                    "利用者名カナ",
                    "利用者名",
                    "1",
                    "19881222",
                    "190-0023",
                    "住所１",
                    "住所２",
                    "090-1234-5678",
                    "20200102",
                    "20200103",
                    "20200104",
                    "2",
                    "20200105",
                    "11",
                    "99999",
                    "11",
                    "12",
                    "13",
                    "21",
                    "22",
                    "23",
                    "31",
                    "32",
                    "33",
                    "41",
                    "42",
                    "43",
                    "51",
                    "52",
                    "53",
                    "61",
                    "62",
                    "63",
                    "71",
                    "72",
                    "73",
                    "81",
                    "82",
                    "83",
                    "91",
                    "92",
                    "93",
                    "101",
                    "102",
                    "103",
                    "111",
                    "112",
                    "113",
                    "121",
                    "122",
                    "123",
                    "131",
                    "132",
                    "133",
                    "88888",
                    "80000",
                    "15",
                    "30",
                    "13",
                    "2222233333",
                    "20200106",
                    "202001071200",
                    "2222244444",
                    "20200108",
                    "20200109",
                    "20200110",
                    "1",
                    "202002"
                ).toTypedArray()
            )

        // expect
        assertEquals("123456", insuredPersonAppendixCsvLine.insureNumber)
        assertEquals("H123456789", insuredPersonAppendixCsvLine.insuredNumber)
        assertEquals("20200101", insuredPersonAppendixCsvLine.carePlanCreationDate)
        assertEquals("利用者名カナ", insuredPersonAppendixCsvLine.nameKana)
        assertEquals("利用者名", insuredPersonAppendixCsvLine.name)
        assertEquals("1", insuredPersonAppendixCsvLine.sex)
        assertEquals("19881222", insuredPersonAppendixCsvLine.birth)
        assertEquals("190-0023", insuredPersonAppendixCsvLine.postalCode)
        assertEquals("住所１", insuredPersonAppendixCsvLine.address1)
        assertEquals("住所２", insuredPersonAppendixCsvLine.address2)
        assertEquals("090-1234-5678", insuredPersonAppendixCsvLine.phoneNumber)
        assertEquals("20200102", insuredPersonAppendixCsvLine.certifiedDate)
        assertEquals("20200103", insuredPersonAppendixCsvLine.creditLimitApplyStartDate)
        assertEquals("20200104", insuredPersonAppendixCsvLine.creditLimitApplyEndDate)
        assertEquals("2", insuredPersonAppendixCsvLine.certifiedStatus)
        assertEquals("20200105", insuredPersonAppendixCsvLine.careLevelChangedDate)
        assertEquals("11", insuredPersonAppendixCsvLine.careLevel)
        assertEquals("99999", insuredPersonAppendixCsvLine.creditLimit)
        assertEquals("11", insuredPersonAppendixCsvLine.creditLimitVisitCare)
        assertEquals("12", insuredPersonAppendixCsvLine.totalUnitVisitCare)
        assertEquals("13", insuredPersonAppendixCsvLine.unitOverCreditLimitVisitCare)
        assertEquals("21", insuredPersonAppendixCsvLine.creditLimitVisitCareBathing)
        assertEquals("22", insuredPersonAppendixCsvLine.totalUnitVisitCareBathing)
        assertEquals("23", insuredPersonAppendixCsvLine.unitOverCreditLimitVisitCareBathing)
        assertEquals("31", insuredPersonAppendixCsvLine.creditLimitVisitNursing)
        assertEquals("32", insuredPersonAppendixCsvLine.totalUnitVisitNursing)
        assertEquals("33", insuredPersonAppendixCsvLine.unitOverCreditLimitVisitNursing)
        assertEquals("41", insuredPersonAppendixCsvLine.creditLimitVisitRehabilitation)
        assertEquals("42", insuredPersonAppendixCsvLine.totalUnitVisitRehabilitation)
        assertEquals("43", insuredPersonAppendixCsvLine.unitOverCreditLimitVisitRehabilitation)
        assertEquals("51", insuredPersonAppendixCsvLine.creditLimitDayCare)
        assertEquals("52", insuredPersonAppendixCsvLine.totalUnitDayCare)
        assertEquals("53", insuredPersonAppendixCsvLine.unitOverCreditLimitDayCare)
        assertEquals("61", insuredPersonAppendixCsvLine.creditLimitDayCareRehabilitation)
        assertEquals("62", insuredPersonAppendixCsvLine.totalUnitDayCareRehabilitation)
        assertEquals("63", insuredPersonAppendixCsvLine.unitOverCreditLimitDayCareRehabilitation)
        assertEquals("71", insuredPersonAppendixCsvLine.creditLimitWelfareEquipment)
        assertEquals("72", insuredPersonAppendixCsvLine.totalUnitWelfareEquipment)
        assertEquals("73", insuredPersonAppendixCsvLine.unitOverCreditLimitWelfareEquipment)
        assertEquals("81", insuredPersonAppendixCsvLine.creditLimitStayLifeCare)
        assertEquals("82", insuredPersonAppendixCsvLine.totalUnitStayLifeCare)
        assertEquals("83", insuredPersonAppendixCsvLine.unitOverCreditLimitStayLifeCare)
        assertEquals("91", insuredPersonAppendixCsvLine.creditLimitStayMedicalCare)
        assertEquals("92", insuredPersonAppendixCsvLine.totalUnitStayMedicalCare)
        assertEquals("93", insuredPersonAppendixCsvLine.unitOverCreditLimitStayMedicalCare)
        assertEquals("101", insuredPersonAppendixCsvLine.creditLimitNightlyVisitCare)
        assertEquals("102", insuredPersonAppendixCsvLine.totalUnitNightlyVisitCare)
        assertEquals("103", insuredPersonAppendixCsvLine.unitOverCreditLimitNightlyVisitCare)
        assertEquals("111", insuredPersonAppendixCsvLine.creditLimitDementiaDayCare)
        assertEquals("112", insuredPersonAppendixCsvLine.totalUnitDementiaDayCare)
        assertEquals("113", insuredPersonAppendixCsvLine.unitOverCreditLimitDementiaDayCare)
        assertEquals("121", insuredPersonAppendixCsvLine.creditLimitDementiaLifeCare)
        assertEquals("122", insuredPersonAppendixCsvLine.totalUnitDementiaLifeCare)
        assertEquals("123", insuredPersonAppendixCsvLine.unitOverCreditLimitDementiaLifeCare)
        assertEquals("131", insuredPersonAppendixCsvLine.creditLimitCommunityVisitCare)
        assertEquals("132", insuredPersonAppendixCsvLine.totalUnitCommunityVisitCare)
        assertEquals("133", insuredPersonAppendixCsvLine.unitOverCreditLimitCommunityVisitCare)
        assertEquals("88888", insuredPersonAppendixCsvLine.totalUnitOverCreditLimit)
        assertEquals("80000", insuredPersonAppendixCsvLine.stayOverUnitCreditLimit)
        assertEquals("15", insuredPersonAppendixCsvLine.stayDaysPreviousMonth)
        assertEquals("30", insuredPersonAppendixCsvLine.stayAccumlationDays)
        assertEquals("13", insuredPersonAppendixCsvLine.previousCareLevel)
        assertEquals("2222233333", insuredPersonAppendixCsvLine.agreementOffice)
        assertEquals("20200106", insuredPersonAppendixCsvLine.registrationDate)
        assertEquals("202001071200", insuredPersonAppendixCsvLine.timeStamp)
        assertEquals("2222244444", insuredPersonAppendixCsvLine.updatedOffice)
        assertEquals("20200108", insuredPersonAppendixCsvLine.servicePlanCreatedDate)
        assertEquals("20200109", insuredPersonAppendixCsvLine.servicePlanAppendixCreatedDate)
        assertEquals("20200110", insuredPersonAppendixCsvLine.servicePlanNotificationDate)
        assertEquals("1", insuredPersonAppendixCsvLine.identification)
        assertEquals("202002", insuredPersonAppendixCsvLine.targetDate)
    }
}