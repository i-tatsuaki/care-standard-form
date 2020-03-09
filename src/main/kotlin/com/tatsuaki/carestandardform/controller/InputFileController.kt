package com.tatsuaki.carestandardform.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class InputFileController() {
    @GetMapping("servicePlan")
    fun servicePlan(): String {
        return "servicePlan"
    }

    @GetMapping("servicePlanAppendix")
    fun servicePlanAppendix(): String {
        return "servicePlanAppendix"
    }
}