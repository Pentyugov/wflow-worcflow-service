package com.pentyugov.wflow.workflowservice.core.system.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.bson.UuidRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@Configuration
@EnableMongoAuditing
public class MongoDbConfiguration extends AbstractMongoClientConfiguration {

    @Value("${spring.data.mongodb.uri}")
    String mongoUri;
    @Value("${spring.data.mongodb.database}")
    String database;

    @Override
    public MongoClient mongoClient() {
        ConnectionString connectionString = new ConnectionString(mongoUri);
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .uuidRepresentation(UuidRepresentation.STANDARD)
                .applyConnectionString(connectionString).build();
        return MongoClients.create(mongoClientSettings);
    }

    @Override
    protected String getDatabaseName() {
        return database;
    }

    @Bean
    public EntityMongoTemplate entityMongoTemplate() {
        return new EntityMongoTemplate(mongoClient(), getDatabaseName());
    }

}
