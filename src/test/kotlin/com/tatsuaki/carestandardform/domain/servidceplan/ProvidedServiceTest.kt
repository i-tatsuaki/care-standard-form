package com.tatsuaki.carestandardform.domain.servidceplan

import org.junit.Test
import java.time.LocalTime
import com.tatsuaki.carestandardform.domain.serviceplan.ProvidedService
import com.tatsuaki.carestandardform.domain.serviceplan.Office
import kotlin.test.assertEquals

class ProvidedServiceTest {
    @Test
    fun getMethodTest() {
        // given
        val office = Office("とある通所介護事業所", "03-1234-5678")
        val providedService = ProvidedService(
            LocalTime.of(10, 0),
            LocalTime.of(12, 0),
            "通所介護１",
            office,
            setOf(1, 3, 5),
            setOf(1, 5)
        )

        // expect
        val expected1 = Int::class.javaObjectType
        val expected : Int? = expected1.cast(Integer.valueOf(1))
        assertEquals(LocalTime.of(10, 0), providedService.from)
        assertEquals(LocalTime.of(12, 0), providedService.to)
        assertEquals("通所介護１", providedService.serviceName)
        assertEquals(office, providedService.office)
        assertEquals(setOf(1, 3, 5), providedService.provideDaysPlan)
        assertEquals(setOf(1, 5), providedService.provideDaysResult)
        assertEquals("1", providedService.getPlanDay(1))
        assertEquals("", providedService.getPlanDay(2))
        assertEquals("1", providedService.getResultDay(5))
        assertEquals("", providedService.getResultDay(3))
    }
}
