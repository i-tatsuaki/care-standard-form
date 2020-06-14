package com.tatsuaki.carestandardform.domain.model

enum class Sex(val division: String) {
    男("1"),
    女("2");

    companion object {
        fun fromDivision(division: String): Sex {
            return values().firstOrNull {
                it.division == division
            } ?: throw Exception() // TODO 業務エラーを実装
        }
    }
}