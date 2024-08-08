package ru.tbank.smartdata.analyse.payment.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "debezium.target")
data class DebeziumTargetDatabaseProperties(
    val hostname: String,
    val port: String,
    val dbname: String,
    val username: String,
    val password: String
)
