package ru.tbank.smartdata.analyse.payment.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.tbank.smartdata.analyse.payment.repository.StatisticRepository

@Service
class StatisticService(
    private val statisticRepository: StatisticRepository
) {

    fun getAllStatistic() = statisticRepository.findAll()

    @Transactional
    fun increment(status: String) = statisticRepository.incrementAmount(status)

    @Transactional
    fun reduce(status: String) = statisticRepository.reduceAmount(status)

}
