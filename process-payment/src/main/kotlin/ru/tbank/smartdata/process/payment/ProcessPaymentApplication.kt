package ru.tbank.smartdata.process.payment

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.boot.runApplication
import org.springframework.context.event.EventListener
import ru.tbank.smartdata.process.payment.service.DataGenerationService

@SpringBootApplication
class ProcessPaymentApplication(
    private val dataGenerationService: DataGenerationService
) {

    @EventListener(ApplicationReadyEvent::class)
    fun main() = dataGenerationService.generateData()

}

fun main(args: Array<String>) {
    runApplication<ProcessPaymentApplication>(*args)
}
