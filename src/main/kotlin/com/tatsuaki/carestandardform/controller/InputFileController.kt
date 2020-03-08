package com.tatsuaki.carestandardform.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class InputFileController() {
    @GetMapping
    fun init(): String {
        return "servicePlan"
    }
}