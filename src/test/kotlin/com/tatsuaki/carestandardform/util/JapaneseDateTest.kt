package com.tatsuaki.carestandardform.util

import org.junit.Test
import java.time.LocalDate
import kotlin.test.assertEquals

class JapaneseDateTest {

    @Test
    fun getMethodTest() {
        // given
        val japaneseDate = JapaneseDate(
            LocalDate.of(2019, 12, 1)
        )

        // expect
        assertEquals(2019, japaneseDate.getYear())
        assertEquals(12, japaneseDate.getMonthValue())
        assertEquals(1, japaneseDate.getDayOfMonth())
        assertEquals("平成", japaneseDate.getEra())
    }
}