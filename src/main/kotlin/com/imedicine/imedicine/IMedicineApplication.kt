package com.imedicine.imedicine

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class IMedicineApplication

fun main(args: Array<String>) {
    runApplication<IMedicineApplication>(*args)
}
