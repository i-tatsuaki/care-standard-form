package com.tatsuaki.carestandardform.controller

import com.tatsuaki.carestandardform.domain.serviceplan.ServicePlan
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
    fun servicePlanAppendix(): String {
        return "servicePlanAppendix"
    }
}