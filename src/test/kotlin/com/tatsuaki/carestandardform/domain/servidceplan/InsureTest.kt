package com.tatsuaki.carestandardform.domain.servidceplan

import com.tatsuaki.carestandardform.domain.serviceplan.Insure
import org.junit.Test
import kotlin.test.assertEquals

class InsureTest {

    @Test
    fun getMethodTest() {
        // when
        val insure = Insure("123456", "江戸川区")

        // then
        assertEquals("123456", insure.number)
        assertEquals("江戸川区", insure.name)
    }
}