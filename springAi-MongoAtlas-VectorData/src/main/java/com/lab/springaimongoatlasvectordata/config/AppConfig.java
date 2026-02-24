package com.lab.springaimongoatlasvectordata.config;

import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.vectorstore.mongodb.atlas.MongoDBAtlasVectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;


@Configuration
public class AppConfig {

    @Bean
    public VectorStore mongoDbVectorStore(MongoTemplate mongoTemplate, EmbeddingModel embeddingModel){

        return MongoDBAtlasVectorStore.builder(mongoTemplate, embeddingModel).build();
    }

}
