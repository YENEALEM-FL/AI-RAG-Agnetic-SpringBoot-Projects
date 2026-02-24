package com.lab.springaiobservabilty;

import org.springframework.ai.document.Document;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.Resource;

import javax.validation.Valid;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class SpringAiObservabilityApplication implements CommandLineRunner {

    @Value("classpath:input.txt")
    Resource resource;

    private final VectorStore vectorStore;

    public SpringAiObservabilityApplication(VectorStore vectorStore){
        this.vectorStore = vectorStore;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringAiObservabilityApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        List<Document> documents = Files.lines(resource.getFile().toPath())
                .map(Document::new)
                .toList();
        TextSplitter textSplitter = new TokenTextSplitter();
        for(Document document: documents) {

            List<Document> splittedDocument = textSplitter.split(document);
            vectorStore.add(splittedDocument);
            Thread.sleep(1000);
        }
    }
}
