package com.tatsuaki.carestandardform.domain.servidceplan

import com.tatsuaki.carestandardform.domain.serviceplan.Office
import org.junit.Test
import kotlin.test.assertEquals

class OfficeTest {

    @Test
    fun getMethodTest() {
        // when
        val office = Office("ある居宅介護支援事業所", "03-1234-5678")

        // then
        assertEquals("ある居宅介護支援事業所", office.name)
        assertEquals( "03-1234-5678", office.telephoneNumber)
    }
}