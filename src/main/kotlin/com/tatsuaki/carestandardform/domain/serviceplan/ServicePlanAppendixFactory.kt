package com.tatsuaki.carestandardform.domain.serviceplan

import com.tatsuaki.carestandardform.domain.model.csv.InsuredPersonAppendixCsvLine
import com.tatsuaki.carestandardform.domain.model.csv.ServicePlanAppendixCsvLine
import com.tatsuaki.carestandardform.domain.serviceplanappendix.ServicePlanAppendix
import kotlin.streams.toList

class ServicePlanAppendixFactory {
    fun createServicePlanAppendixes(
        insuredPersonAppendixCsvLines: ArrayList<InsuredPersonAppendixCsvLine>,
        servicePlanCsvAppendixLines: ArrayList<ServicePlanAppendixCsvLine>
    ) : List<ServicePlanAppendix> {
        val servicePlanAppendixes: ArrayList<ServicePlanAppendix> = arrayListOf()
        insuredPersonAppendixCsvLines.stream().forEach { insuredPersonAppendixCsvLine ->
            // 利用者補足の１行に対応する６票の行を抽出
            val targetServicePlanAppendixes = servicePlanCsvAppendixLines.stream().filter {
                it.isCorrespond(insuredPersonAppendixCsvLine)
            }.toList()
            servicePlanAppendixes.add(create(insuredPersonAppendixCsvLine, targetServicePlanAppendixes))
        }
        return servicePlanAppendixes
    }

    private fun create(
        insuredPersonAppendixCsvLine: InsuredPersonAppendixCsvLine,
        servicePlanAppendixCsvLine: List<ServicePlanAppendixCsvLine>
    ) : ServicePlanAppendix {
        return ServicePlanAppendix()
    }
}