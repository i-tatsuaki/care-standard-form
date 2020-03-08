package com.tatsuaki.carestandardform

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class CareStandardFormApplication {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(CareStandardFormApplication::class.java, *args)
        }
    }
}