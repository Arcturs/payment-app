package ru.tbank.smartdata.analyse.payment.config

import io.debezium.config.CommonConnectorConfig.TOPIC_PREFIX
import io.debezium.connector.postgresql.PostgresConnectorConfig.PLUGIN_NAME
import io.debezium.embedded.EmbeddedEngineConfig.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.util.ResourceUtils

@Configuration
class DebeziumConfig(
    private val debeziumTargetDatabaseProperties: DebeziumTargetDatabaseProperties
) {

    @Bean
    fun debeziumConfiguration(): io.debezium.config.Configuration =
        io.debezium.config.Configuration.create()
            .with(ENGINE_NAME, "analyse-payment-engine")
            .with(TOPIC_PREFIX, "analyse-payment-topic")
            .with(CONNECTOR_CLASS, "io.debezium.connector.postgresql.PostgresConnector")
            .with(OFFSET_STORAGE, "org.apache.kafka.connect.storage.FileOffsetBackingStore")
            .with(OFFSET_STORAGE_FILE_FILENAME, ResourceUtils.getFile("classpath:offsets.dat").absolutePath)
            .with(DATABASE_HOSTNAME_PROPERTY, debeziumTargetDatabaseProperties.hostname)
            .with(DATABASE_PORT_PROPERTY, debeziumTargetDatabaseProperties.port)
            .with(DATABASE_USER_PROPERTY, debeziumTargetDatabaseProperties.username)
            .with(DATABASE_PASSWORD_PROPERTY, debeziumTargetDatabaseProperties.password)
            .with(DATABASE_DBNAME_PROPERTY, debeziumTargetDatabaseProperties.dbname)
            .with(PLUGIN_NAME, "pgoutput")
            .build()

    private companion object {

        const val DATABASE_HOSTNAME_PROPERTY = "database.hostname"
        const val DATABASE_PORT_PROPERTY = "database.port"
        const val DATABASE_USER_PROPERTY = "database.user"
        const val DATABASE_PASSWORD_PROPERTY = "database.password"
        const val DATABASE_DBNAME_PROPERTY = "database.dbname"

    }

}
