package com.taxdown.managementtool.infrastructure.config;

import com.mongodb.reactivestreams.client.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

@Configuration
public class MongoConfiguration {  //extends AbstractMongoClientConfiguration

    @Value("${spring.data.mongodb.uri}")
    private String mongoUri;


    @Autowired
    MongoClient mongoClient;

    
    protected String getDatabaseName() {
        return "customerdb";
    }
    
    /*
    
    @Override
    public MongoClient mongoClient() {
        MongoClients.create()
        return MongoClients.create(mongoUri);
    }

    public ReactiveMongoTemplate reactiveMongoTemplate() {
        return new ReactiveMongoTemplate(mongoClient(), getDatabaseName());
    }
    
 */


    @Bean
    public ReactiveMongoTemplate reactiveMongoTemplate() {
        return new ReactiveMongoTemplate(mongoClient, this.getDatabaseName());
    }
}
