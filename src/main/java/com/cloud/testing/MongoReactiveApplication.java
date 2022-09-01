package com.cloud.testing;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@EnableReactiveMongoRepositories
public class MongoReactiveApplication
        extends AbstractReactiveMongoConfiguration {
    @Value("${mongo.url}")
    private String url;
    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create(url);
    }

    @Override
    protected String getDatabaseName() {
        return "mongo";
    }
}