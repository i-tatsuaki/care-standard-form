package com.tatsuaki.carestandardform.controller

import com.tatsuaki.carestandardform.domain.model.csv.InsuredPersonAppendixCsvLineFactory
import com.tatsuaki.carestandardform.domain.model.csv.ServicePlanCsvLineFactory
import com.tatsuaki.carestandardform.domain.serviceplan.ServicePlanFactory
import com.tatsuaki.carestandardform.domain.serviceplanappendix.ServicePlanAppendix
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.multipart.MultipartFile

@Controller
class InputFileController() {
    @PostMapping("servicePlan")
    fun servicePlan(
        @RequestParam("insuredPersonAppendixFile") insuredPersonAppendix: MultipartFile,
        @RequestParam("servicePlanFile") servicePlanFile: MultipartFile,
        @RequestParam("servicePlanAppendixFile") servicePlanAppendixFile: MultipartFile,
        model: Model
    ): String {

        val servicePlans = ServicePlanFactory().createServicePlans(
            InsuredPersonAppendixCsvLineFactory().create(insuredPersonAppendix),
            ServicePlanCsvLineFactory().create(servicePlanFile)
        )
        model.addAttribute("servicePlan", servicePlans[0]) // TODO 複数行対応

        val servicePlanAppendix = ServicePlanAppendix()
        model.addAttribute("servicePlanAppendix", servicePlanAppendix) // TODO 複数行対応

        return "servicePlan"
    }

    @GetMapping
    fun inputFile(): String{
        return "inputFile"
    }
}