package com.tatsuaki.carestandardform.domain.serviceplanappendix

import com.tatsuaki.carestandardform.domain.model.InsureLicense
import com.tatsuaki.carestandardform.domain.model.InsuredPerson
import com.tatsuaki.carestandardform.util.JapaneseDate
import java.time.LocalDate
import java.time.YearMonth

class ServicePlanAppendix {

    val serviceProvisionYearMonth: YearMonth
    val creationDate: LocalDate
    val insuredPerson: InsuredPerson

    init {
        // TODO 仮実装
        this.serviceProvisionYearMonth = YearMonth.of(2020, 4)
        this.creationDate = LocalDate.of(2020, 3, 1)
        this.insuredPerson = InsuredPerson(
            "利用者氏名",
            "リヨウシャシメイ",
            "男",
            JapaneseDate(LocalDate.of(1988, 12, 22)),
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
    }


}
