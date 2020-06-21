package com.tatsuaki.carestandardform.domain.model

import org.junit.Test
import kotlin.test.assertEquals

class KindUnitNumberManagementTest {
    @Test
    fun getMethodTest() {
        // given
        val kindUnitNumberManagement = KindUnitNumberManagement("11", "1", "2", "3")

        // expect
        assertEquals("11", kindUnitNumberManagement.serviceKindCode)
        assertEquals("1", kindUnitNumberManagement.baseUnitNumber)
        assertEquals("2", kindUnitNumberManagement.totalUnitNumber)
        assertEquals("3", kindUnitNumberManagement.overUnitNumber)

    }
}