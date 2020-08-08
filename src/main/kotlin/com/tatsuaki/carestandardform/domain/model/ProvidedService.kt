package com.tatsuaki.carestandardform.domain.model

import java.time.LocalTime

data class ProvidedService(val careService: CareService, val office: Office, val providedTime: ProvidedTime)

class ProvidedTime() {
    val planResults = mutableMapOf<Time, PlanResult>()

    fun addPlan(time: Time, day: Int) : ProvidedTime {
        if (!planResults.containsKey(time)) {
            // 必ず初期化する必要あり（別箇所で初期化を前提に実装している）
            planResults[time] = PlanResult()
        }
        // 必ず初期化するように作られているため!!で強制実行している
        planResults[time]!!.addPlan(day)

        return this
    }

    fun addResult(time: Time, day: Int) : ProvidedTime {
        if (!planResults.containsKey(time)) {
            // 必ず初期化する必要あり（別箇所で初期化を前提に実装している）
            planResults[time] = PlanResult()
        }
        // 必ず初期化するように作られているため!!で強制実行している
        planResults.get(time)!!.addResult(day)

        return this
    }

    fun getPlanDays(time: Time) : Set<Int> {
        return planResults[time]?.getPlanDays() ?: emptySet()
    }

    fun getResultDays(time: Time) : Set<Int> {
        return planResults[time]?.getResultDays() ?: emptySet()
    }

    fun getPlanTotalCount() : Int {
        return planResults.map {
            it.value.getPlanDays().size
        }.sum()
    }

    fun getResultTotalCount() : Int {
        return planResults.map {
            it.value.getResultDays().size
        }.sum()
    }

    class Time(
        val from: String,
        val to: String
    ) {
        override fun equals(other: Any?): Boolean {
            when (other is Time) {
                true -> return (this.from.equals(other.from) && this.to.equals(other.to))
                false -> return false
            }
        }

        override fun hashCode(): Int {
            val initialValue = 17
            val valueWithFrom = 31 * initialValue + from.hashCode()
            return 31 * valueWithFrom + to.hashCode()
        }
    }

    class PlanResult {
        private val provideDaysPlan = mutableSetOf<Int>()
        private val provideDaysResult = mutableSetOf<Int>()

        fun addPlan(day: Int) {
            provideDaysPlan.add(day)
        }

        fun addResult(day: Int) {
            provideDaysResult.add(day)
        }

        fun getPlanDays() : Set<Int> {
            return provideDaysPlan.toSet()
        }

        fun getResultDays() : Set<Int> {
            return provideDaysResult.toSet()
        }

        fun getPlanDay(day: Int?) : String {
            return if(provideDaysPlan.contains(day)) "1" else ""
        }

        fun getResultDay(day: Int?) : String {
            return if(provideDaysResult.contains(day)) "1" else ""
        }

    }
}
