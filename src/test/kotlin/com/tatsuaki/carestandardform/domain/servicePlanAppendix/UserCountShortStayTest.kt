package com.tatsuaki.carestandardform.domain.servicePlanAppendix

import com.tatsuaki.carestandardform.domain.serviceplanappendix.UserCountShortStay
import org.junit.Test
import kotlin.test.assertEquals

class UserCountShortStayTest {
    @Test
    fun getMethodTest() {
        // given
        val userCountShortStay = UserCountShortStay(1, 2, 3)

        // expect
        assertEquals(1, userCountShortStay.previousMonthCount)
        assertEquals(2, userCountShortStay.thisMonthCount)
        assertEquals(3, userCountShortStay.accumulateCount)
    }
}