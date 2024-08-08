package ru.tbank.smartdata.process.payment

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.boot.runApplication
import org.springframework.context.event.EventListener
import ru.tbank.smartdata.process.payment.entity.Payment
import ru.tbank.smartdata.process.payment.service.PaymentService

@SpringBootApplication
class ProcessPaymentApplication(private val paymentService: PaymentService) {

    @EventListener(ApplicationReadyEvent::class)
    fun main() {
        paymentService.createPayments(
            listOf(
                Payment(

                )
            )
        )
    }
}

fun main(args: Array<String>) {
    runApplication<ProcessPaymentApplication>(*args)
}
