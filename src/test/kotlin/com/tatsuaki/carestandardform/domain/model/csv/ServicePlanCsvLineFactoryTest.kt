package com.tatsuaki.carestandardform.domain.model.csv

import org.junit.Test
import org.springframework.mock.web.MockMultipartFile
import java.lang.StringBuilder
import java.nio.charset.Charset
import kotlin.test.assertEquals

class ServicePlanCsvLineFactoryTest {

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
        val result = ServicePlanCsvLineFactory().create(inputFile)

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
        val result = ServicePlanCsvLineFactory().create(inputFile)

        // then
        assertEquals("ケアマネさん", result[0].creationPersonName)
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
        val result = ServicePlanCsvLineFactory().create(inputFile)

        // then
        assertEquals("ケアマネ,さん", result[1].creationPersonName)
    }

    private fun createCsvLine() : String {
        val builder = StringBuilder()
        builder
            .append("201910,223456,H123456789,20200101,202002,1234567890,\"ケアマネさん\",18,9,20200202,0000000000000000000011111111,1000,1100,4,112233,1,0987654321,サービス事業所です,5,1,2233445566,4")
            .append("\n")
            .append("201910,223456,H123456789,20200101,202002,1234567890,\"ケアマネ,さん\",18,9,20200204,0000000000000000000011111111,1000,1200,4,112233,1,0987654321,サービス事業所です,5,1,2233445566,4")
        return builder.toString()
    }
}