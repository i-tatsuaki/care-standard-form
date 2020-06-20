package com.tatsuaki.carestandardform.domain

import java.time.YearMonth
import java.time.format.TextStyle
import java.util.*

class ServiceProvisionYearMonth(val yearMonth: YearMonth) {
    fun getMapDayToDayOfWeek() : Map<Int,String> {
        return (1..yearMonth.atEndOfMonth().dayOfMonth)
            .map {
                it to yearMonth.atDay(it).dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.JAPANESE)
            }.toMap()
    }
}
