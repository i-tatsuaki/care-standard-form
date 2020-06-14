package com.tatsuaki.carestandardform.domain.model

import com.tatsuaki.carestandardform.util.JapaneseDate

data class InsuredPerson(
    val name: String,
    val nameKana: String,
    val sex: Sex,
    val birthDate: JapaneseDate,
    val insureLicense: InsureLicense
)