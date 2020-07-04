package com.tatsuaki.carestandardform.domain.model

import org.junit.Test
import java.time.LocalDate
import kotlin.test.assertEquals

class InsureLicenseTest {

    @Test
    fun getMethodTest() {
        // given
        val insureLicense = InsureLicense(
            "H123456789",
            CareLevel.要介護１,
            CareLevel.要支援１,
            LocalDate.of(2019, 12, 1),
            12345,
            LocalDate.of(2019, 12, 2),
            LocalDate.of(2020, 11, 30)
        )

        // expect
        assertEquals("H123456789", insureLicense.number)
        assertEquals(CareLevel.要介護１, insureLicense.careLevel)
        assertEquals(CareLevel.要支援１, insureLicense.previousCareLevel)
        assertEquals(LocalDate.of(2019, 12, 1), insureLicense.careLevelUpdateDate)
        assertEquals(12345, insureLicense.creditLimit)
        assertEquals(LocalDate.of(2019, 12, 2), insureLicense.creditLimitFrom)
        assertEquals(LocalDate.of(2020, 11, 30), insureLicense.creditLimitTo)
    }
}