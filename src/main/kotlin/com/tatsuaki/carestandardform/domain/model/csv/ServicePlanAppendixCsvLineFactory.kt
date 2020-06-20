package com.tatsuaki.carestandardform.domain.model.csv

import com.opencsv.CSVReader
import org.springframework.web.multipart.MultipartFile
import java.io.IOException
import java.nio.charset.Charset

class ServicePlanAppendixCsvLineFactory {
    fun create(servicePlanAppendixFile: MultipartFile) : ArrayList<ServicePlanAppendixCsvLine> {
        val servicePlanAppendixCsvLine : ArrayList<ServicePlanAppendixCsvLine> = arrayListOf()
        try {
            CSVReader(servicePlanAppendixFile.inputStream.bufferedReader(Charset.forName("SJIS"))).use { csvReader ->
                csvReader.forEach {
                    servicePlanAppendixCsvLine.add(ServicePlanAppendixCsvLine(it))
                }
            }
        } catch (e: IOException) {
            System.err.println(e.message)
        }
        return servicePlanAppendixCsvLine
    }
}