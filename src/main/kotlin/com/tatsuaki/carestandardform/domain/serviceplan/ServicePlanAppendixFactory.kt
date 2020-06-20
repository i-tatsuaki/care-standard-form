package com.tatsuaki.carestandardform.domain.serviceplan

import com.tatsuaki.carestandardform.domain.model.csv.InsuredPersonAppendixCsvLine
import com.tatsuaki.carestandardform.domain.model.csv.ServicePlanAppendixCsvLine
import com.tatsuaki.carestandardform.domain.serviceplanappendix.ServicePlanAppendix

class ServicePlanAppendixFactory {
    fun createServicePlanAppendixes(
        insuredPersonAppendixCsvLines: ArrayList<InsuredPersonAppendixCsvLine>,
        servicePlanCsvAppendixLine: ArrayList<ServicePlanAppendixCsvLine>
    ) : List<ServicePlanAppendix> {
        return listOf(ServicePlanAppendix())
    }
}