package com.tatsuaki.carestandardform.domain.model

class ProvidedServices(val list: List<ProvidedService>) {
    fun countProvidedServiceKind() : Int {
        return list.sumBy { it.providedTime.planResults.size }
    }
}
