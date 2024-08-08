package ru.tbank.smartdata.analyse.payment.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.math.BigDecimal

@JsonIgnoreProperties(ignoreUnknown = true)
data class PaymentChangeLogRecord(
    var id: Long? = null,
    var payer: String? = null,
    var recipient: String? = null,
    var amount: BigDecimal? = null,
    var status: String? = null,
    var comment: String? = null
)
