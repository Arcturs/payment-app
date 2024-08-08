package ru.tbank.smartdata.analyse.payment.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import ru.tbank.smartdata.analyse.payment.entity.Statistic.Companion.TABLE_NAME

@Table(value = TABLE_NAME)
class Statistic {

    @get:Id
    @get:Column
    var id: Long? = null

    @get:Column
    var status: String? = null

    @get:Column
    var amount: Int? = null

    override fun equals(other: Any?) = id == (other as? Statistic)?.id

    override fun hashCode() = id.hashCode()

    override fun toString() = "Statistic(id=$id, " +
            "status=${status}, " +
            "amount=$amount)"

    companion object {
        const val TABLE_NAME = "statistics"
    }

}
