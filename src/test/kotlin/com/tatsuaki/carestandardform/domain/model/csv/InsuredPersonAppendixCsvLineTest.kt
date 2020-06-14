package com.tatsuaki.carestandardform.domain.model.csv

import org.junit.Test
import kotlin.test.assertEquals

class InsuredPersonAppendixCsvLineTest {

    @Test
    fun getMethodTest() {
        // given
        val insuredPersonAppendixCsvLine =
            InsuredPersonAppendixCsvLine(
                arrayListOf<String>(
                    "201910",
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
                    "サービス種類1",
                    "11",
                    "12",
                    "13",
                    "サービス種類2",
                    "21",
                    "22",
                    "23",
                    "サービス種類3",
                    "31",
                    "32",
                    "33",
                    "サービス種類4",
                    "41",
                    "42",
                    "43",
                    "サービス種類5",
                    "51",
                    "52",
                    "53",
                    "サービス種類6",
                    "61",
                    "62",
                    "63",
                    "サービス種類7",
                    "71",
                    "72",
                    "73",
                    "サービス種類8",
                    "81",
                    "82",
                    "83",
                    "サービス種類9",
                    "91",
                    "92",
                    "93",
                    "88888",
                    "15",
                    "30",
                    "13",
                    "2222233333",
                    "20200106",
                    "2222244444",
                    "1",
                    "202002"
                ).toTypedArray()
            )

        // expect
        assertEquals("201910", insuredPersonAppendixCsvLine.csvVersion)
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
        assertEquals("サービス種類1", insuredPersonAppendixCsvLine.serviceDivision1)
        assertEquals("11", insuredPersonAppendixCsvLine.creditLimit1)
        assertEquals("12", insuredPersonAppendixCsvLine.totalUnit1)
        assertEquals("13", insuredPersonAppendixCsvLine.unitOverCreditLimit1)
        assertEquals("サービス種類2", insuredPersonAppendixCsvLine.serviceDivision2)
        assertEquals("21", insuredPersonAppendixCsvLine.creditLimit2)
        assertEquals("22", insuredPersonAppendixCsvLine.totalUnit2)
        assertEquals("23", insuredPersonAppendixCsvLine.unitOverCreditLimit2)
        assertEquals("サービス種類3", insuredPersonAppendixCsvLine.serviceDivision3)
        assertEquals("31", insuredPersonAppendixCsvLine.creditLimit3)
        assertEquals("32", insuredPersonAppendixCsvLine.totalUnit3)
        assertEquals("33", insuredPersonAppendixCsvLine.unitOverCreditLimit3)
        assertEquals("サービス種類4", insuredPersonAppendixCsvLine.serviceDivision4)
        assertEquals("41", insuredPersonAppendixCsvLine.creditLimit4)
        assertEquals("42", insuredPersonAppendixCsvLine.totalUnit4)
        assertEquals("43", insuredPersonAppendixCsvLine.unitOverCreditLimit4)
        assertEquals("サービス種類5", insuredPersonAppendixCsvLine.serviceDivision5)
        assertEquals("51", insuredPersonAppendixCsvLine.creditLimit5)
        assertEquals("52", insuredPersonAppendixCsvLine.totalUnit5)
        assertEquals("53", insuredPersonAppendixCsvLine.unitOverCreditLimit5)
        assertEquals("サービス種類6", insuredPersonAppendixCsvLine.serviceDivision6)
        assertEquals("61", insuredPersonAppendixCsvLine.creditLimit6)
        assertEquals("62", insuredPersonAppendixCsvLine.totalUnit6)
        assertEquals("63", insuredPersonAppendixCsvLine.unitOverCreditLimit6)
        assertEquals("サービス種類7", insuredPersonAppendixCsvLine.serviceDivision7)
        assertEquals("71", insuredPersonAppendixCsvLine.creditLimit7)
        assertEquals("72", insuredPersonAppendixCsvLine.totalUnit7)
        assertEquals("73", insuredPersonAppendixCsvLine.unitOverCreditLimit7)
        assertEquals("サービス種類8", insuredPersonAppendixCsvLine.serviceDivision8)
        assertEquals("81", insuredPersonAppendixCsvLine.creditLimit8)
        assertEquals("82", insuredPersonAppendixCsvLine.totalUnit8)
        assertEquals("83", insuredPersonAppendixCsvLine.unitOverCreditLimit8)
        assertEquals("サービス種類9", insuredPersonAppendixCsvLine.serviceDivision9)
        assertEquals("91", insuredPersonAppendixCsvLine.creditLimit9)
        assertEquals("92", insuredPersonAppendixCsvLine.totalUnit9)
        assertEquals("93", insuredPersonAppendixCsvLine.unitOverCreditLimit9)
        assertEquals("88888", insuredPersonAppendixCsvLine.totalUnitOverCreditLimit)
        assertEquals("15", insuredPersonAppendixCsvLine.stayDaysPreviousMonth)
        assertEquals("30", insuredPersonAppendixCsvLine.stayAccumulationDays)
        assertEquals("13", insuredPersonAppendixCsvLine.previousCareLevel)
        assertEquals("2222233333", insuredPersonAppendixCsvLine.agreementOffice)
        assertEquals("20200106", insuredPersonAppendixCsvLine.registrationDate)
        assertEquals("2222244444", insuredPersonAppendixCsvLine.updatedOffice)
        assertEquals("1", insuredPersonAppendixCsvLine.identification)
        assertEquals("202002", insuredPersonAppendixCsvLine.serviceYearMonth)
    }
}