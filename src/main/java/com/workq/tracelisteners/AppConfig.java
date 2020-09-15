package com.workq.tracelisteners;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppConfig.class);
    private static final String KAFKA_CONFIG_KEY = "kafka.topic.name";
    private static final String KAFKA_SERVERS_KEY = "kafka.servers";
    private static final String KAFKA_CLIENT_ID = "kafka.client.id";
    private static final String CONFIG_FILE = "/config.properties";

//    private final Properties config;

    public AppConfig() {

//        LOGGER.info("Loading app config");
//
//        config = new Properties();
//        try(InputStream istream = getClass().getResourceAsStream(CONFIG_FILE)) {
//            config.load(istream);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        LOGGER.info("Config = {}", config.toString());
//        LOGGER.info("Done loading app config");
    }

    public String kafkaServers() {
        return "localhost:9092";

    }

    public String processTraceTopic() {
        return "trace-events";
    }

    public String taskTraceTopic() {
        return "trace-events";
    }

    public String slaTraceTopic() {
        return "trace-events";
    }

    public String kafkaClientId() {
        return "pam-trace-publisher";
    }

}
