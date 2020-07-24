package com.tatsuaki.carestandardform.domain.model.csv

import org.junit.Test
import org.springframework.mock.web.MockMultipartFile
import java.lang.StringBuilder
import java.nio.charset.Charset
import kotlin.test.assertEquals

class ServicePlanAppendixCsvLineFactoryTest {

    @Test
    fun createSizeTest() {
        // given
        val inputFile = MockMultipartFile(
            "insuredPersonAppendix.csv",
            "insuredPersonAppendix.csv",
            "text/csv",
            createCsvLine().toByteArray(Charset.forName("SJIS"))
        )

        // when
        val result = ServicePlanAppendixCsvLineFactory().create(inputFile)

        // then
        assertEquals(2, result.size)
    }

    @Test
    fun createFreeTextTest() {
        // 入力されるcsvのフリーテキストはダブルクォーテーションで囲われているが、それを除いて認識すること

        // given
        val inputFile = MockMultipartFile(
            "insuredPersonAppendix.csv",
            "insuredPersonAppendix.csv",
            "text/csv",
            createCsvLine().toByteArray(Charset.forName("SJIS"))
        )

        // when
        val result = ServicePlanAppendixCsvLineFactory().create(inputFile)

        // then
        assertEquals("ケアマネ事業所", result[0].serviceOfficeName)
    }

    @Test
    fun createFreeTextCommaTest() {
        // 入力されるcsvのフリーテキストにカンマが含まれていても正しく判別すること

        // given
        val inputFile = MockMultipartFile(
            "insuredPersonAppendix.csv",
            "insuredPersonAppendix.csv",
            "text/csv",
            createCsvLine().toByteArray(Charset.forName("SJIS"))
        )

        // when
        val result = ServicePlanAppendixCsvLineFactory().create(inputFile)

        // then
        assertEquals("ケアマネ,事業所", result[1].serviceOfficeName)
    }

    private fun createCsvLine() : String {
        val builder = StringBuilder()
        builder
            .append("201910,223456,H123456789,20200101,202002,\"ケアマネ事業所\",0987654321,5,112233,18,10,17,3,54,1,2,3,4,10.00,100,90,90,0,10,0,5,15,18,,9999999999,1234509876,20200620,9999988888,")
            .append("\n")
            .append("201910,223456,0123456789,20200101,202002,\"ケアマネ,事業所\",0987654321,5,112233,18,10,17,3,54,1,2,3,4,10.00,100,90,90,0,10,0,5,15,18,,9999999999,1234509876,20200620,9999988888,")
        return builder.toString()
    }
}