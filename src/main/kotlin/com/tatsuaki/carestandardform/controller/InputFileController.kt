package com.tatsuaki.carestandardform.controller

import com.tatsuaki.carestandardform.domain.serviceplan.ServicePlan
import com.tatsuaki.carestandardform.domain.serviceplanappendix.ServicePlanAppendix
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class InputFileController() {
    @GetMapping("servicePlan")
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
}