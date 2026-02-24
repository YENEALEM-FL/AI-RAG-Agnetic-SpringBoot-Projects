package com.lab.storingairedisvecorstore.controller;

import org.springframework.ai.document.Document;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

@RestController
public class AppController {

    @Autowired
    VectorStore vectorStore;

    @Value("classpath:input.txt")
    Resource resource;

    @GetMapping("/load")
    public String load() throws IOException, InterruptedException{

        List<Document> documents = Files.lines(resource.getFile().toPath())
                .map(line -> new Document(line, Map.of("mata1", "meta2")))
                .toList();

        TextSplitter textSplitter = new TokenTextSplitter();

        for(Document document: documents){

            List<Document> splittedList = textSplitter.split(document);
            System.out.println("Before adding document: "+ splittedList);
            vectorStore.add(splittedList);
            System.out.println("After document is added: "+ document.getFormattedContent());
        }
        return "Loaded: " + resource.getFilename();
    }

    @GetMapping("/search")
    public String search(){

        List<Document> result = vectorStore
                .similaritySearch(SearchRequest
                .builder()
                .query("Bike for small kids")
                .topK(2)
                .build());

        return result.toString();
    }

}
