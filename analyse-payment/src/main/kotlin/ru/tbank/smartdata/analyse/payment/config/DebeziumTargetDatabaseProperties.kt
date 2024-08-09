package ru.tbank.smartdata.analyse.payment.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "debezium.target")
data class DebeziumTargetDatabaseProperties(
    var hostname: String? = null,
    var port: String? = null,
    var dbname: String? = null,
    var username: String? = null,
    var password: String? = null,
)
