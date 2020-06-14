package com.tatsuaki.carestandardform.domain.model

enum class CareLevel(val division: String) {
    事業対象者("06"),
    要支援１("12"),
    要支援２("13"),
    要介護１("21"),
    要介護２("22"),
    要介護３("23"),
    要介護４("24"),
    要介護５("25");

    companion object {
        fun fromDivision(division: String): CareLevel {
            return CareLevel.values().firstOrNull {
                it.division == division
            } ?: throw Exception() // TODO 業務エラーを実装
        }
    }
}
