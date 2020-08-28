package com.workq.tracelisteners.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.workq.tracelisteners.AppConfig;
import com.workq.tracelisteners.events.ProcessTraceEvent;
import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Publishes a {@link ProcessTraceEvent} to kafka
 */
public class KafkaPublisher implements MessagePublisher {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaPublisher.class);
    private final Producer<Long, String> producer;
    private final AppConfig config;
    private final ObjectMapper mapper;


    public KafkaPublisher() {
        config = new AppConfig();
        mapper = new ObjectMapper();
        producer = createProducer();
    }

    private Producer<Long, String> createProducer() {

        Thread.currentThread().setContextClassLoader(null);
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, config.kafkaServers());
        props.put(ProducerConfig.CLIENT_ID_CONFIG, config.kafkaClientId());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return new KafkaProducer<>(props);
    }

    /**
     * Asynchronously publish a trace event message to Kafka.
     * @param event The message to publish
     * @throws PublishingFailedException if the message could not be published.
     */
    @Override
    public void publishMessage(ProcessTraceEvent event) throws PublishingFailedException {

        long key;
        String value;
        try {
            key = Long.parseLong(event.getProcessInstanceId());
            value = mapper.writeValueAsString(event);
        } catch (JsonProcessingException e) {
            LOGGER.error("Failed to publish message {}", event.toString());
            throw new PublishingFailedException(e);
        }

        LOGGER.info("topic = {}, key = {}", config.kafkaTopic(), key);

        final ProducerRecord<Long, String> record = new ProducerRecord<>(config.kafkaTopic(), key, value);
        producer.send(record, ((meta, exception) -> {
            if (meta != null) {
                LOGGER.info("Sent message");
//                LOGGER.debug("Sent record({}, {}), metadata({}, {})", key, value, meta.partition(), meta.offset());
            } else {
                LOGGER.error("Failed to publish message({}, {})", key, value);
                LOGGER.error("", exception);
            }
        }));

        producer.flush();
    }
}
