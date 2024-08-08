package ru.tbank.smartdata.process.payment.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import ru.tbank.smartdata.process.payment.entity.Payment.Companion.TABLE_NAME
import java.math.BigDecimal

@Table(value = TABLE_NAME)
class Payment {

    @get:Id
    @get:Column
    var id: Long? = null

    @get:Column
    var payer: String? = null

    @get:Column
    var recipient: String? = null

    @get:Column
    var amount: BigDecimal? = null

    @get:Column
    var status: String? = null

    @get:Column
    var comment: String? = null

    override fun equals(other: Any?) = id == (other as? Payment)?.id

    override fun hashCode() = id.hashCode()

    override fun toString() = "Payment(id=$id, " +
            "payer=$payer, " +
            "recipient=$recipient, " +
            "amount=$amount, " +
            "paymentStatus=${status}, " +
            "comment=$comment)"

    companion object {
        const val TABLE_NAME = "payments"
    }

}
