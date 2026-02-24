package com.lab.springaielasticsearchvectordatastore.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.document.Document;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class ElasticSearchService {

    private static final Logger logger = LoggerFactory.getLogger(ElasticSearchService.class);

    @Autowired
    VectorStore vectorStore;

    @Value("classpath:products.json")
    Resource resource;

    public void load() throws IOException {

        List<Document> documents = readAndPrintJsonFile();

        TextSplitter textSplitter = new TokenTextSplitter();

    documents.forEach(
        doc -> {
          List<Document> splitDocuments = textSplitter.split(doc);
          logger.info("Before data {} added.", splitDocuments);
          try {
            TimeUnit.SECONDS.sleep(20);
          } catch (InterruptedException e) {
              Thread.currentThread().interrupt();
            throw new RuntimeException(e);
          }
          System.out.println("Adding document: "+ doc.getText());
          logger.info("After data {} added.", splitDocuments);
          vectorStore.add(splitDocuments);

        });
    }

    public List<Document> search(String query){

        return vectorStore.similaritySearch(SearchRequest
                .builder()
                .query(query)
                .topK(2).build());
    }

    public List<Document> readAndPrintJsonFile() throws IOException {

        List<Document> documents = new ArrayList<>();

        try(InputStream inputStream = resource.getInputStream()){

            ObjectMapper objectMapper = new ObjectMapper();

            JsonNode jsonNode = objectMapper.readTree(inputStream);

            for(JsonNode node: jsonNode){

                if (node.has("description")){

                    System.out.println(node.get("description").toString());
                    documents.add(new Document(node.get("description").toString()));
                }
            }
        }
        return documents;
    }

}


