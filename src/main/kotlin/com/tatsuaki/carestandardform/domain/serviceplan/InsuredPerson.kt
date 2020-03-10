package com.tatsuaki.carestandardform.domain.serviceplan

import com.tatsuaki.carestandardform.util.JapaneseDate

data class InsuredPerson(
    val name: String,
    val nameKana: String,
    val sex: String,
    val birthDate: JapaneseDate,
    val insureLicense: InsureLicense
)