package com.tatsuaki.carestandardform.domain.serviceplan

import java.time.LocalDate
import java.time.YearMonth
import java.time.chrono.JapaneseDate

class ServicePlan() {

    val serviceProvisionYearMonth: YearMonth
    val creationDate: LocalDate
    val insure: Insure
    val careManager: CareManager
    val insuredPerson: InsuredPerson
    val notificationDate: LocalDate
    val shortStayUseDaysOfPreviousMonth: Int

    init {
        // TODO 仮実装
        this.serviceProvisionYearMonth = YearMonth.of(2020, 3)
        this.creationDate = LocalDate.of(2020, 3, 1)
        this.insure = Insure("123456", "江戸川区")
        this.careManager = CareManager(
            "ケアマネ氏名",
            Office("ある居宅介護支援事業所", "03-1234-5678")
        )
        this.insuredPerson = InsuredPerson(
            "利用者氏名",
            "リヨウシャシメイ",
            "男",
            com.tatsuaki.carestandardform.util.JapaneseDate(LocalDate.of(1988, 12, 22)),
            InsureLicense(
                "H123456789",
                "要介護3",
                "要介護1",
                LocalDate.of(2019, 11, 30),
                16592,
                LocalDate.of(2018, 12, 1),
                LocalDate.of(2019, 12, 1)
            )
        )
        this.notificationDate = LocalDate.of(2020, 1, 1)
        this.shortStayUseDaysOfPreviousMonth = 13
    }
}
