package com.tatsuaki.carestandardform.domain.model.csv

import org.junit.Test
import org.springframework.mock.web.MockMultipartFile
import java.lang.StringBuilder
import java.nio.charset.Charset
import kotlin.test.assertEquals

class InsuredPersonAppendixCsvLineFactoryTest {

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
        val result = InsuredPersonAppendixCsvLineFactory().create(inputFile)

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
        val result = InsuredPersonAppendixCsvLineFactory().create(inputFile)

        // then
        assertEquals("ヨシザワカスミ", result[0].nameKana)
        assertEquals("芳澤かすみ", result[0].name)
        assertEquals("住所1", result[0].address1)
        assertEquals("住所2", result[0].address2)
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
        val result = InsuredPersonAppendixCsvLineFactory().create(inputFile)

        // then
        assertEquals("ヌクミ,メル", result[1].nameKana)
        assertEquals("生見,愛瑠", result[1].name)
        assertEquals("住所,1", result[1].address1)
        assertEquals("住所,2", result[1].address2)
    }

    private fun createCsvLine() : String {
        val builder = StringBuilder()
        builder
            .append("201910,223456,H123456789,,\"ヨシザワカスミ\",\"芳澤かすみ\",2,19881222,,\"住所1\",\"住所2\",,,20200401,20200430,,19881222,23,12345,11,100,200,300,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,14,,12,,,,,202002")
            .append("\n")
            .append("201910,223456,0123456789,,\"ヌクミ,メル\",\"生見,愛瑠\",2,19881222,,\"住所,1\",\"住所,2\",,,20200401,20200430,,19881222,23,12345,11,100,200,300,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,14,,12,,,,,202002")
        return builder.toString()
    }
}