package com.tatsuaki.carestandardform.domain.serviceplan

import com.opencsv.CSVReader
import com.tatsuaki.carestandardform.domain.model.*
import com.tatsuaki.carestandardform.domain.model.csv.InsuredPersonAppendixCsvLine
import com.tatsuaki.carestandardform.util.JapaneseDate
import org.springframework.web.multipart.MultipartFile
import java.io.IOException
import java.math.BigDecimal
import java.nio.charset.Charset
import java.time.LocalDate
import java.time.LocalTime
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.*

class ServicePlanFactory {
    fun create(insuredPersonAppendixFile: MultipartFile, servicePlanFile: MultipartFile): List<ServicePlan> {

        val servicePlans : ArrayList<ServicePlan> = arrayListOf()

        try {
            CSVReader(insuredPersonAppendixFile.inputStream.bufferedReader(Charset.forName("SJIS"))).use { csvReader ->
                csvReader.forEach {
                    val insuredPersonAppendixCsvLine = InsuredPersonAppendixCsvLine(it)

                    // TODO 仮実装
                    val serviceProvisionYearMonth = YearMonth.of(2020, 3)
                    val creationDate = LocalDate.of(2020, 3, 1)
                    val insure = Insure(insuredPersonAppendixCsvLine.insureNumber, "江戸川区") // TODO 保険者名をどこから取得するか
                    val careManager = CareManager(
                        "ケアマネ氏名",
                        Office("ある居宅介護支援事業所", "1234567890", "03-1234-5678")
                    )
                    val insuredPerson = InsuredPerson(
                        insuredPersonAppendixCsvLine.name,
                        insuredPersonAppendixCsvLine.nameKana,
                        Sex.fromDivision(insuredPersonAppendixCsvLine.sex),
                        JapaneseDate(LocalDate.of(1988, 12, 22)),
                        InsureLicense(
                            "H123456789",
                            CareLevel.fromDivision(insuredPersonAppendixCsvLine.careLevel),
                            "要介護1", // TODO どこの項目を入れるか確認
                            LocalDate.of(2019, 11, 30), // TODO どこの項目を入れるか確認
                            insuredPersonAppendixCsvLine.creditLimit.toInt(),
                            LocalDate.parse(
                                insuredPersonAppendixCsvLine.creditLimitApplyStartDate, DateTimeFormatter.ofPattern("yyyyMMdd")),
                            LocalDate.parse(
                                insuredPersonAppendixCsvLine.creditLimitApplyEndDate, DateTimeFormatter.ofPattern("yyyyMMdd"))
                        )
                    )
                    val notificationDate = LocalDate.parse(
                        insuredPersonAppendixCsvLine.servicePlanNotificationDate, DateTimeFormatter.ofPattern("yyyyMMdd"))
                    val shortStayUseDaysOfPreviousMonth = insuredPersonAppendixCsvLine.stayDaysPreviousMonth.toInt()

                    val time = ProvidedTime.Time(
                        LocalTime.of(10, 0),
                        LocalTime.of(12, 0)
                    )
                    val providedServices = listOf(
                        ProvidedService(
                            CareService(
                                "通所介護１",
                                "131111",
                                132,
                                90,
                                119,
                                488,
                                30,
                                458,
                                58,
                                400,
                                BigDecimal(10.0),
                                4880,
                                90,
                                4392,
                                488,
                                488
                            ),
                            Office("とある通所介護事業所", "1234567890", "03-1234-5678"),
                            ProvidedTime()
                                .addPlan(time, 1)
                                .addPlan(time, 3)
                                .addPlan(time, 5)
                                .addResult(time, 1)
                                .addResult(time, 5)
                        ),
                        ProvidedService(
                            CareService(
                                "通所介護２",
                                "131111",
                                132,
                                90,
                                119,
                                488,
                                30,
                                458,
                                58,
                                400,
                                BigDecimal(10.0),
                                4880,
                                90,
                                4392,
                                488,
                                488
                            ),
                            Office("とある通所介護事業所", "1234567890", "03-1234-5678"),
                            ProvidedTime()
                                .addPlan(time, 2)
                                .addPlan(time, 4)
                                .addPlan(time, 6)
                                .addResult(time, 2)
                                .addResult(time, 4)
                        ),
                        ProvidedService(
                            CareService(
                                "通所介護３",
                                "131111",
                                132,
                                90,
                                119,
                                488,
                                30,
                                458,
                                58,
                                400,
                                BigDecimal(10.0),
                                4880,
                                90,
                                4392,
                                488,
                                488
                            ),
                            Office("とある通所介護事業所", "1234567890", "03-1234-5678"),
                            ProvidedTime()
                                .addPlan(time, 3)
                                .addPlan(time, 6)
                                .addPlan(time, 9)
                                .addResult(time, 3)
                                .addResult(time, 9)
                        )
                    )

                    servicePlans.add(
                        ServicePlan(
                            serviceProvisionYearMonth,
                            creationDate,
                            insure,
                            careManager,
                            insuredPerson,
                            notificationDate,
                            shortStayUseDaysOfPreviousMonth,
                            providedServices
                        )
                    )
                }
            }
        } catch (e: IOException) {
            System.err.println(e.message)
        }

        return servicePlans
    }
}