package com.lab.springaimetadatapgvector.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.lab.springaimetadatapgvector.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.document.Document;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.vectorstore.filter.FilterExpressionBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class AiService {

    private static final Logger logger = LoggerFactory.getLogger(AiService.class);

    @Autowired
    VectorStore vectorStore;

    @Value("classpath:products.json")
    Resource resource;

    public List<Document> loadData() throws IOException {

        List<Document> documents = readAndPrintJsonFile();

        TextSplitter textSplitter = new TokenTextSplitter();

        documents.forEach(
                doc -> {
                    List<Document> splitDocuments = textSplitter.split(doc);
                    try {
                        System.out.println("Adding document: " + doc.getText());
                        vectorStore.add(splitDocuments);
                        TimeUnit.SECONDS.sleep(20);
                        System.out.println("After data {} added." + splitDocuments);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        throw new RuntimeException(e);
                    }
                });
        return documents;
    }

    public List<Document> searchUsingInAndNin(String query) {
        FilterExpressionBuilder filter = new FilterExpressionBuilder();

        return vectorStore.similaritySearch(SearchRequest
                .builder()
                .query(query)
                .topK(2)
                .filterExpression(filter.and(filter.and(filter.in("brand", "Apple", "Dell"),filter.nin("price", 670.00, 800.00, 1000.00)), filter.ne("price", 1000.00)).build()).build());
    }
    public List<Document> searchUsingComparison(String query) {
        FilterExpressionBuilder filter = new FilterExpressionBuilder();

        return vectorStore.similaritySearch(SearchRequest
                .builder()
                .query(query)
                .topK(2)
                .filterExpression(filter.and(filter.and(filter.eq("brand", "Apple"),filter.gt("price", 700.00)), filter.lt("price", 1000.00)).build()).build());
    }

    public List<Document> searchUsingNot(String query) {
        FilterExpressionBuilder filter = new FilterExpressionBuilder();

        return vectorStore.similaritySearch(SearchRequest
                .builder()
                .query(query)
                .topK(2)
                .filterExpression(filter.not(filter.eq( "brand","Apple")).build()).build());
    }

    public List<Document> readAndPrintJsonFile() throws IOException {

        List<Document> documents = new ArrayList<>();
        List<Product> products = getProducts();

        for (Product product : products) {

            Document document = new Document(product.getBrand() + " " + product.getProductName(),
                     Map.of("product_name", product.getProductName(),
                            "brand", product.getBrand(),
                            "price", product.getPrice(),
                            "country", product.getCountry(),
                            "description", product.getDescription()));
            documents.add(document);
        }

        return documents;
    }

    public List<Product> getProducts() throws IOException {

        List<Product> products = new ArrayList<>();

        try (InputStream inputStream = resource.getInputStream()) {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(inputStream);

            for (JsonNode node : jsonNode) {
                Product product = objectMapper.treeToValue(node, Product.class);
                products.add(product);
            }
        }
        return products;
    }

}
