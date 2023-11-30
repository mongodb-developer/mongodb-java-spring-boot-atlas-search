package com.mongodb.starter;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

@Configuration
public class MongoClientConfig extends AbstractMongoClientConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(MongoClientConfig.class);
    @Value("${spring.data.mongodb.database}")
    private String database;
    @Value("${spring.data.mongodb.uri}")
    private String connectionString;

    @Override
    protected String getDatabaseName() {
        return database;
    }

    @Bean
    public MongoClientSettings mongoClientSettings() {
        LOGGER.info("=> Creating the MongoClientSettings for MongoClient & MongoTemplate.");
        return MongoClientSettings.builder().applyConnectionString(new ConnectionString(connectionString)).build();
    }
}
