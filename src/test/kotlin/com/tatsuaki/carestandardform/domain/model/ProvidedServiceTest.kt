package com.tatsuaki.carestandardform.domain.model

import com.tatsuaki.carestandardform.domain.model.CareService
import org.junit.Test
import java.time.LocalTime
import com.tatsuaki.carestandardform.domain.model.ProvidedService
import com.tatsuaki.carestandardform.domain.model.Office
import com.tatsuaki.carestandardform.domain.model.ProvidedTime
import java.math.BigDecimal
import kotlin.test.assertEquals

class ProvidedServiceTest {
    @Test
    fun ProvidedServiceGetMethodTest() {
        // given
        val office = Office("とある通所介護事業所", "1234567890", "03-1234-5678")
        val careService = CareService("通所介護１", "131111", 132, 90, 119, 488, 30, 458, 58, 400, BigDecimal(10.0), 4880, 90, 4392, 488, 488)
        val providedDate = ProvidedTime()

        val providedService = ProvidedService(
            careService,
            office,
            providedDate
        )

        // expect
        assertEquals(careService, providedService.careService)
        assertEquals(office, providedService.office)
        assertEquals(providedDate, providedService.providedTime)
    }

    @Test
    fun ProvidedDateTest() {
        // given
        val time = ProvidedTime.Time(
            LocalTime.of(10, 0),
            LocalTime.of(12, 0)
        )
        val nonServiceTime = ProvidedTime.Time(
            LocalTime.of(10, 0),
            LocalTime.of(11, 0)
        )
        val providedDate = ProvidedTime()
            .addPlan(time, 1)
            .addPlan(time, 2)
            .addPlan(time, 3)
            .addResult(time, 4)
            .addResult(time, 5)

        // expect
        assertEquals(3, providedDate.getPlanTotalCount())
        assertEquals(2,  providedDate.getResultTotalCount())
        assertEquals(setOf(1, 2, 3),  providedDate.getPlanDays(time))
        assertEquals(setOf(),  providedDate.getPlanDays(nonServiceTime))
        assertEquals(setOf(4, 5),  providedDate.getResultDays(time))
        assertEquals(setOf(),  providedDate.getResultDays(nonServiceTime))
        assertEquals("1",  providedDate.planResults[time]?.getPlanDay(1))
        assertEquals("",  providedDate.planResults[time]?.getPlanDay(4))
        assertEquals("1",  providedDate.planResults[time]?.getResultDay(4))
        assertEquals("",  providedDate.planResults[time]?.getResultDay(1))
    }
}
