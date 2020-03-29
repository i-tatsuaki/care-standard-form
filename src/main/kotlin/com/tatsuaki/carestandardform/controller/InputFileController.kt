package com.tatsuaki.carestandardform.controller

import com.tatsuaki.carestandardform.domain.serviceplan.ServicePlan
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

        val servicePlan1 = ServicePlan(insuredPersonAppendix, servicePlanFile, servicePlanAppendixFile)
        model.addAttribute("servicePlan", servicePlan1)
        return "servicePlan"
    }

    @GetMapping("servicePlanAppendix")
    fun servicePlanAppendix(model: Model): String {
        val servicePlanAppendix = ServicePlanAppendix()
        model.addAttribute("servicePlanAppendix", servicePlanAppendix)
        return "servicePlanAppendix"
    }

    @GetMapping
    fun inputFile(): String{
        return "inputFile"
    }
}