package com.tatsuaki.carestandardform.domain.model

import com.tatsuaki.carestandardform.util.JapaneseDate
import org.junit.Test
import java.time.LocalDate
import kotlin.test.assertEquals

class InusuredPersonTest {

    @Test
    fun getMethodTest() {
        // given
        val insuredPerson = InsuredPerson(
            "利用者名",
            "リヨウシャメイ",
            "男",
            JapaneseDate(LocalDate.of(1988, 12, 22)),
            InsureLicense(
                "H123456789",
                "要介護1",
                "要支援1",
                LocalDate.of(2019, 12, 1),
                12345,
                LocalDate.of(2019, 12, 2),
                LocalDate.of(2020, 11, 30)
            )
        )

        // expect
        assertEquals("利用者名", insuredPerson.name)
        assertEquals("リヨウシャメイ", insuredPerson.nameKana)
        assertEquals("男", insuredPerson.sex)
        assertEquals(LocalDate.of(1988, 12, 22), insuredPerson.birthDate.date)
        assertEquals("H123456789", insuredPerson.insureLicense.number)
        assertEquals("要介護1", insuredPerson.insureLicense.careLevel)
        assertEquals("要支援1", insuredPerson.insureLicense.previousCareLevel)
        assertEquals(LocalDate.of(2019, 12, 1), insuredPerson.insureLicense.careLevelUpdateDate)
        assertEquals(12345, insuredPerson.insureLicense.creditLimit)
        assertEquals(LocalDate.of(2019, 12, 2), insuredPerson.insureLicense.creditLimitFrom)
        assertEquals(LocalDate.of(2020, 11, 30), insuredPerson.insureLicense.creditLimitTo)
    }
}