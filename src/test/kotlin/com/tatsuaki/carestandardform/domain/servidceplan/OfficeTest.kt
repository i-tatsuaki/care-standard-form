package com.tatsuaki.carestandardform.domain.servidceplan

import com.tatsuaki.carestandardform.domain.model.Office
import org.junit.Test
import kotlin.test.assertEquals

class OfficeTest {

    @Test
    fun getMethodTest() {
        // given
        val office = Office("ある居宅介護支援事業所", "03-1234-5678")

        // expect
        assertEquals("ある居宅介護支援事業所", office.name)
        assertEquals( "03-1234-5678", office.telephoneNumber)
    }
}