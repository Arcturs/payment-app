package ru.tbank.smartdata.analyse.payment.repository

import org.springframework.data.jdbc.repository.query.Modifying
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.tbank.smartdata.analyse.payment.entity.Statistic

@Repository
interface StatisticRepository : CrudRepository<Statistic, Long> {

    @Modifying
    @Query(
        """
        update statistics
        set amount = amount + 1
        where status = :status
        """
    )
    fun incrementAmount(status: String)

    @Modifying
    @Query(
        """
        update statistics
        set amount = amount - 1
        where status = :status
        """
    )
    fun reduceAmount(status: String)

}
