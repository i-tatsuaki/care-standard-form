package com.tatsuaki.carestandardform.domain.model.csv

import com.opencsv.CSVReader
import org.springframework.web.multipart.MultipartFile
import java.io.IOException
import java.nio.charset.Charset
import java.util.ArrayList

class ServicePlanCsvLineFactory {
    fun create(servicePlanFile: MultipartFile) : ArrayList<ServicePlanCsvLine> {
        val servicePlanCsvLines : ArrayList<ServicePlanCsvLine> = arrayListOf()
        try {
            CSVReader(servicePlanFile.inputStream.bufferedReader(Charset.forName("SJIS"))).use { csvReader ->
                csvReader.forEach {
                    servicePlanCsvLines.add(ServicePlanCsvLine(it))
                }
            }
        } catch (e: IOException) {
            System.err.println(e.message)
        }
        return servicePlanCsvLines
    }

}
