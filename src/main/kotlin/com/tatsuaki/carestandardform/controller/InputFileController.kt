package com.tatsuaki.carestandardform.controller

import com.tatsuaki.carestandardform.domain.serviceplan.ServicePlan
import com.tatsuaki.carestandardform.domain.serviceplanappendix.ServicePlanAppendix
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping

@Controller
class InputFileController() {
    @PostMapping("servicePlan")
    fun servicePlan(model: Model): String {
        val servicePlan = ServicePlan()
        model.addAttribute("servicePlan", servicePlan)
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