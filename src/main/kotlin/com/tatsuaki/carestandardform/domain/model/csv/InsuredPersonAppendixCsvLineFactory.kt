package com.tatsuaki.carestandardform.domain.model.csv

import com.opencsv.CSVReader
import org.springframework.web.multipart.MultipartFile
import java.io.IOException
import java.nio.charset.Charset
import java.util.ArrayList

class InsuredPersonAppendixCsvLineFactory {
    fun create(insuredPersonAppendixFile: MultipartFile) : ArrayList<InsuredPersonAppendixCsvLine> {
        val insuredPersonAppendixCsvLines : ArrayList<InsuredPersonAppendixCsvLine> = arrayListOf()
        try {
            CSVReader(insuredPersonAppendixFile.inputStream.bufferedReader(Charset.forName("SJIS"))).use { csvReader ->
                csvReader.forEach {
                    insuredPersonAppendixCsvLines.add(InsuredPersonAppendixCsvLine(it))
                }
            }
        } catch (e: IOException) {
            System.err.println(e.message)
        }
        return insuredPersonAppendixCsvLines
    }

}
