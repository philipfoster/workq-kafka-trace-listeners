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
    private static final String CONFIG_FILE = "config.properties";

    private Properties config;

    public AppConfig() {

        LOGGER.info("Loading app config");
        File configFile = new File(CONFIG_FILE);

        config = new Properties();
        try(InputStream istream = new FileInputStream(configFile)) {
            config.load(istream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        LOGGER.info("Done loading app config");
    }

    public String kafkaServers() {
        String result =  config.getProperty(KAFKA_SERVERS_KEY);
        if (result == null) {
            throw new RuntimeException("Could not load configuration " + KAFKA_SERVERS_KEY);
        }
        return result;

    }

    public String kafkaTopic() {
        String result =  config.getProperty(KAFKA_CONFIG_KEY, null);
        if (result == null) {
            throw new RuntimeException("Could not load configuration " + KAFKA_CONFIG_KEY);
        }
        return result;

    }


    public String kafkaClientId() {
        String result =  config.getProperty(KAFKA_CLIENT_ID, null);
        if (result == null) {
            throw new RuntimeException("Could not load configuration " + KAFKA_CLIENT_ID);
        }
        return result;

    }

}
