package com.tatsuaki.carestandardform.domain.model

import com.tatsuaki.carestandardform.domain.model.CareManager
import com.tatsuaki.carestandardform.domain.model.Office
import org.junit.Test
import kotlin.test.assertEquals

class CareManagerTest {

    @Test
    fun getMethodTest() {
        // given
        val careManager = CareManager(
            "ケアマネ氏名",
            Office("ある居宅介護支援事業所", "1234567890", "03-1234-5678")
        )

        // expect
        assertEquals("ケアマネ氏名", careManager.name)
        assertEquals("ある居宅介護支援事業所", careManager.office.name)
        assertEquals("03-1234-5678", careManager.office.telephoneNumber)
    }
}