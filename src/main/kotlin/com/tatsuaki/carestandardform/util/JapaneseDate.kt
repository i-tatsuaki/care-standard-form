package com.tatsuaki.carestandardform.util

import java.time.LocalDate
import java.time.chrono.JapaneseDate
import java.time.format.DateTimeFormatter

data class JapaneseDate(val date: LocalDate) {

    fun getYear() : Int {
        return date.year
    }

    fun getMonthValue() : Int {
        return date.monthValue
    }

    fun getDayOfMonth() : Int {
        return date.dayOfMonth
    }

    fun getEra() : String {
        val japaneseDate = JapaneseDate.from(date)
        val eraFormatter = DateTimeFormatter.ofPattern("G")
        return eraFormatter.format(japaneseDate)
    }

    fun getEraYear(): String {
        val japaneseDate = JapaneseDate.from(date)
        return DateTimeFormatter.ofPattern("y").format(japaneseDate)
    }
}