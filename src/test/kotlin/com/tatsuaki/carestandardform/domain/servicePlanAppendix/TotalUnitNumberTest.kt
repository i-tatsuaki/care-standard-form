package com.tatsuaki.carestandardform.domain.servicePlanAppendix

import com.tatsuaki.carestandardform.domain.serviceplan.TotalUnitNumber
import org.junit.Test
import kotlin.test.assertEquals

class TotalUnitNumberTest {
    @Test
    fun getMethodTest() {
        // given
        val totalUnitNumberTest =
            TotalUnitNumber(1, 2, 3, 4, 5, 6, 7, 8, 9)

        // expect
        assertEquals(1, totalUnitNumberTest.totalUnitNumber)
        assertEquals(2, totalUnitNumberTest.overKindUnitNumber)
        assertEquals(3, totalUnitNumberTest.kindUnitNumber)
        assertEquals(4, totalUnitNumberTest.overDivisionUnitNumber)
        assertEquals(5, totalUnitNumberTest.divisionUnitNumber)
        assertEquals(6, totalUnitNumberTest.totalInsuranceDemand)
        assertEquals(7, totalUnitNumberTest.paidInsuranceDemandFromInsurance)
        assertEquals(8, totalUnitNumberTest.paidInsuranceDemandFromInsuredPerson)
        assertEquals(9, totalUnitNumberTest.totalInsuredPersonDemand)

    }
}