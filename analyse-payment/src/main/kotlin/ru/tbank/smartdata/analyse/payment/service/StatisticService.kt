package ru.tbank.smartdata.analyse.payment.service

import org.springframework.stereotype.Service
import ru.tbank.smartdata.analyse.payment.repository.StatisticRepository

@Service
class StatisticService(
    private val statisticRepository: StatisticRepository
) {

    fun getAllStatistic() = statisticRepository.findAll()

    fun increment(status: String) = statisticRepository.incrementAmount(status)

    fun reduce(status: String) = statisticRepository.reduceAmount(status)

}
