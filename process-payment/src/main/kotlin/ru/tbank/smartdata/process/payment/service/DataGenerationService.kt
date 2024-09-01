package ru.tbank.smartdata.process.payment.service

import org.jeasy.random.EasyRandom
import org.jeasy.random.EasyRandomParameters
import org.jeasy.random.randomizers.number.BigDecimalRandomizer
import org.jeasy.random.randomizers.range.LocalDateTimeRangeRandomizer
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.tbank.smartdata.process.payment.entity.Payment
import java.math.BigDecimal
import java.time.LocalDateTime

@Service
class DataGenerationService(
    private val paymentService: PaymentService,
    @Value("\${testing.amount}")
    private val amount: Int
) {

    @Transactional
    fun generateData() {
        log.trace("Генерируется {} рандомных выплат", amount)
        val payments = (0 until amount)
            .map {  EASY_RANDOM.nextObject(Payment::class.java).apply { id = null } }
        val savedPayments = paymentService.createPayments(payments)
        log.trace("Сущности сгенерированы и сохранены. Результат {}", savedPayments)
    }

    private companion object {
        val log = LoggerFactory.getLogger(DataGenerationService::class.java)
        val EASY_RANDOM = EasyRandom(
            EasyRandomParameters()
                .stringLengthRange(1, 50)
                .randomize(
                    LocalDateTime::class.java,
                    LocalDateTimeRangeRandomizer(
                        LocalDateTime.of(2020, 1, 1, 0, 0),
                        LocalDateTime.of(2024, 12, 31, 23, 59)
                    )
                )
                .randomize(BigDecimal::class.java, BigDecimalRandomizer(2 as Int))
        )

    }

}
