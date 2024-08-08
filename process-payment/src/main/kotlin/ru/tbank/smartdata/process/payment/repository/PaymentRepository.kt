package ru.tbank.smartdata.process.payment.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.tbank.smartdata.process.payment.entity.Payment

@Repository
interface PaymentRepository : CrudRepository<Payment, Long>
