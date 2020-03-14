package com.tatsuaki.carestandardform.domain.servidceplan

import com.tatsuaki.carestandardform.domain.serviceplan.InsureLicense
import org.junit.Test
import java.time.LocalDate
import kotlin.test.assertEquals

class InsureLicense {

    @Test
    fun getMethodTest() {
        // given
        val insureLicense = InsureLicense(
                "H123456789",
                "要介護1",
                "要支援1",
                LocalDate.of(2019, 12, 1),
                12345,
                LocalDate.of(2019, 12, 2),
                LocalDate.of(2020, 11, 30)
        )

        // expect
        assertEquals("H123456789", insureLicense.number)
        assertEquals("要介護1", insureLicense.careLevel)
        assertEquals("要支援1", insureLicense.previousCareLevel)
        assertEquals(LocalDate.of(2019, 12, 1), insureLicense.careLevelUpdateDate)
        assertEquals(12345, insureLicense.creditLimit)
        assertEquals(LocalDate.of(2019, 12, 2), insureLicense.creditLimitFrom)
        assertEquals(LocalDate.of(2020, 11, 30), insureLicense.creditLimitTo)
    }
}