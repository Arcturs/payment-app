package ru.tbank.smartdata.process.payment.service

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.tbank.smartdata.process.payment.entity.Payment
import ru.tbank.smartdata.process.payment.repository.PaymentRepository

@Service
class PaymentService(
    val paymentRepository: PaymentRepository
) {

    @Transactional
    fun createPayment(payment: Payment) = paymentRepository.save(payment)

    @Transactional
    fun createPayments(payments: List<Payment>) = paymentRepository.saveAll(payments)

    fun getPayment(id: Long) =
        paymentRepository.findByIdOrNull(id)
            ?: throw NoSuchElementException("Платежа с ИД $id не существует")

    fun getAllPayments() = paymentRepository.findAll()

    @Transactional
    fun deletePayment(id: Long) = paymentRepository.deleteById(id)

}
