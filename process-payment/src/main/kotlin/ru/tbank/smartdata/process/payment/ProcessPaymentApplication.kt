package ru.tbank.smartdata.process.payment

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.boot.runApplication
import org.springframework.context.event.EventListener
import ru.tbank.smartdata.process.payment.entity.Payment
import ru.tbank.smartdata.process.payment.service.PaymentService
import java.math.BigDecimal

@SpringBootApplication
class ProcessPaymentApplication(private val paymentService: PaymentService) {

    @EventListener(ApplicationReadyEvent::class)
    fun main() {
        paymentService.createPayments(
            listOf(
                Payment().apply {
                    payer = "Alice"
                    recipient = "Bob"
                    amount = BigDecimal.valueOf(150.00)
                    status = "NEW"
                }
            )
        )
    }
}

fun main(args: Array<String>) {
    runApplication<ProcessPaymentApplication>(*args)
}
