package com.pentyugov.wflow.workflowservice.core.system.config;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@Configuration
@EnableMongoAuditing
public class MongoDbConfiguration extends AbstractReactiveMongoConfiguration {

    @Value("${spring.data.mongodb.uri}")
    String mongoUri;

    @Override
    public MongoClient reactiveMongoClient() {
        return MongoClients.create(mongoUri);
    }

    @Override
    protected String getDatabaseName() {
        return "wflow";
    }

    @Bean
    public EntityReactiveMongoTemplate entityReactiveMongoTemplate() {
        return new EntityReactiveMongoTemplate(reactiveMongoClient(), getDatabaseName());
    }

}
