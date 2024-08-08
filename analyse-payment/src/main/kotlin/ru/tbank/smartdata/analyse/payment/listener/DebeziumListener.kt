package ru.tbank.smartdata.analyse.payment.listener

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import io.debezium.data.Envelope.Operation
import io.debezium.data.Envelope.FieldName
import io.debezium.embedded.Connect
import io.debezium.engine.DebeziumEngine
import io.debezium.engine.DebeziumEngine.ConnectorCallback
import io.debezium.engine.RecordChangeEvent
import io.debezium.engine.format.ChangeEventFormat
import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy
import org.apache.kafka.connect.data.Field
import org.apache.kafka.connect.data.Struct
import org.apache.kafka.connect.source.SourceRecord
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import ru.tbank.smartdata.analyse.payment.dto.PaymentChangeLogRecord
import ru.tbank.smartdata.analyse.payment.service.StatisticService
import java.util.concurrent.Executors

@Component
class DebeziumListener(
    debeziumConfig: io.debezium.config.Configuration,
    private val objectMapper: ObjectMapper,
    private val statisticService: StatisticService
) {

    private val debeziumEngine = DebeziumEngine
        .create(ChangeEventFormat.of(Connect::class.java))
        .using(debeziumConfig.asProperties())
        .notifying { recordChangeEvent -> handleChangeEvent(recordChangeEvent) }
        .build()

    private fun handleChangeEvent(recordChangeEvent: RecordChangeEvent<SourceRecord>) {
        log.trace("Была получена запись {} из БД payments", recordChangeEvent)
        val recordStruct = recordChangeEvent.record().value() as Struct
        val operation = Operation.forCode(recordStruct.get(FieldName.OPERATION) as String)
        if (IGNORED_OPERATIONS.contains(operation)) {
            log.trace("Запись будет проигнорирована")
            return
        }

        val recordCondition =
            if (operation == Operation.DELETE) FieldName.BEFORE
            else FieldName.AFTER
        val recordConditionStruct = recordStruct.get(recordCondition) as Struct
        val recordFieldMap = mapStructToMap(recordConditionStruct)
        val paymentChangeLog = objectMapper.readValue(
            objectMapper.writeValueAsString(recordFieldMap),
            PaymentChangeLogRecord::class.java
        )

        if (operation == Operation.DELETE) {
            statisticService.reduce(paymentChangeLog.status!!)
        } else {
            statisticService.increment(paymentChangeLog.status!!)
        }
        log.trace("Успешно обработана запись")
    }

    @PostConstruct
    private fun start() {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        Executors.newSingleThreadExecutor().execute(debeziumEngine)
    }

    @PreDestroy
    private fun stop() = debeziumEngine.close()

    private companion object {

        val IGNORED_OPERATIONS = setOf(Operation.READ, Operation.UPDATE)

        val log: Logger = LoggerFactory.getLogger(DebeziumListener::class.java)

        @JvmStatic
        fun mapStructToMap(struct: Struct): Map<String, Any> {
            return struct.schema()
                .fields()
                .map(Field::name)
                .filter { struct.get(it) != null }
                .associateWith { struct.get(it) }
        }

    }

}