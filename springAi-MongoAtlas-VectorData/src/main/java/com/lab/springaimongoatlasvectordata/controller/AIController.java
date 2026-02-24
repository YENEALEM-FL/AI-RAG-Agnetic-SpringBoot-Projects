package com.lab.springaimongoatlasvectordata.controller;

import org.springframework.ai.document.Document;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import java.util.stream.Collectors;


@RestController
public class AIController {

    Logger logger = Logger.getLogger(AIController.class.getName());

    VectorStore vectorStore;

    @Value("classpath:data.txt")
    Resource resource;

    public AIController(VectorStore vectorStore){
        this.vectorStore = vectorStore;
    }

    @GetMapping("/load")
    public String load(){

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))){

            List<Document> documents = reader.lines()
                    .map(Document::new)
                    .toList();

            TokenTextSplitter tokenTextSplitter = new TokenTextSplitter();

      documents.forEach(
          doc -> {
            List<Document> splitDocuments = tokenTextSplitter.split(doc);

            vectorStore.add(splitDocuments);

            try {
                TimeUnit.MILLISECONDS.sleep(20000);
                logger.info("added document: " + doc);
            } catch (InterruptedException e) {
              throw new RuntimeException(e);
            }
          });
       return "loaded: " + resource.getFilename();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @GetMapping("/search")
    public List<String> searchDocuments(@RequestParam(value = "query", defaultValue ="learn how to grow things") String query){
        List<Document> results = vectorStore
                .similaritySearch(SearchRequest.builder().query(query).topK(2).build()
                );

        return results.stream().map(Document::getFormattedContent).collect(Collectors.toList());
    }


}
