package com.tatsuaki.carestandardform.domain.model.csv

import org.junit.Test
import kotlin.test.assertEquals

class ServicePlanCsvLineTest {

    @Test
    fun getMethodTest() {
        // given
        val servicePlanCsvLine =
            ServicePlanCsvLine(
                arrayListOf<String>(
                    "201910",
                    "123456",
                    "H123456789",
                    "20200101",
                    "202002",
                    "1234567890",
                    "ケアマネさん",
                    "18",
                    "9",
                    "20200202",
                    "0000000000000000000011111111",
                    "1000",
                    "1100",
                    "4",
                    "112233",
                    "1",
                    "0987654321",
                    "サービス事業所です",
                    "5",
                    "1",
                    "2233445566",
                    "4"
                ).toTypedArray()
            )

        // expect
        assertEquals("201910", servicePlanCsvLine.csvVersion)
        assertEquals("123456", servicePlanCsvLine.insureNumber)
        assertEquals("H123456789", servicePlanCsvLine.insuredNumber)
        assertEquals("20200101", servicePlanCsvLine.creationDate)
        assertEquals("202002", servicePlanCsvLine.serviceYearMonth)
        assertEquals("1234567890", servicePlanCsvLine.creationOfficeCode)
        assertEquals("ケアマネさん", servicePlanCsvLine.creationPersonName)
        assertEquals("18", servicePlanCsvLine.unitNumber)
        assertEquals("9", servicePlanCsvLine.shortStayDaysByPreviousMonth)
        assertEquals("20200202", servicePlanCsvLine.serviceDate)
        assertEquals("0000000000000000000011111111", servicePlanCsvLine.proratedDays)
        assertEquals("1000", servicePlanCsvLine.serviceStartTime)
        assertEquals("1100", servicePlanCsvLine.serviceEndTime)
        assertEquals("4", servicePlanCsvLine.serviceCount)
        assertEquals("112233", servicePlanCsvLine.serviceCode)
        assertEquals("1", servicePlanCsvLine.serviceIdentification)
        assertEquals("0987654321", servicePlanCsvLine.serviceOfficeCode)
        assertEquals("サービス事業所です", servicePlanCsvLine.serviceOfficeName)
        assertEquals("5", servicePlanCsvLine.numberOfSatellite)
        assertEquals("1", servicePlanCsvLine.isOverThirty)
        assertEquals("2233445566", servicePlanCsvLine.updateOfficeCode)
        assertEquals("4", servicePlanCsvLine.identification)
    }
}