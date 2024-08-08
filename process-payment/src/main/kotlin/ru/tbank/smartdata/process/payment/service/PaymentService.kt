package ru.tbank.smartdata.process.payment.service

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import ru.tbank.smartdata.process.payment.entity.Payment
import ru.tbank.smartdata.process.payment.repository.PaymentRepository

@Service
class PaymentService(
    val paymentRepository: PaymentRepository
) {

    fun createPayment(payment: Payment) = paymentRepository.save(payment)

    fun createPayments(payments: List<Payment>) = paymentRepository.saveAll(payments)

    fun getPayment(id: Long) =
        paymentRepository.findByIdOrNull(id)
            ?: throw NoSuchElementException("Платежа с ИД $id не существует")

    fun getAllPayments() = paymentRepository.findAll()

    fun deletePayment(id: Long) = paymentRepository.deleteById(id)

}
