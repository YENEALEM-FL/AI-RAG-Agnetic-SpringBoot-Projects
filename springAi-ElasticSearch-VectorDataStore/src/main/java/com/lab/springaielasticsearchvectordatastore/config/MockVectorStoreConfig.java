package com.lab.springaielasticsearchvectordatastore.config;

import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.vectorstore.filter.Filter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import java.util.List;

@Configuration
public class MockVectorStoreConfig {

    @Bean
    @Primary
    public VectorStore vectorStore() {
        return new VectorStore() {
            @Override
            public void add(List<Document> documents) {
                System.out.println("Mock VectorStore: Adding " + documents.size() + " documents");
                documents.forEach(doc -> System.out.println("Document: " + doc.getText()));
            }

            @Override
            public void delete(List<String> ids) {
                System.out.println("Mock VectorStore: Deleting documents with IDs: " + ids);
            }

            @Override
            public void delete(Filter.Expression filter) {
                System.out.println("Mock VectorStore: Deleting documents with filter: " + filter);
            }

            @Override
            public List<Document> similaritySearch(SearchRequest request) {
                System.out.println("Mock VectorStore: Searching for: " + request.getQuery());
                return List.of(
                        new Document("Mock document 1 for query: " + request.getQuery()),
                        new Document("Mock document 2 for query: " + request.getQuery())
                );
            }
        };
    }
}
