package com.lab.springaichromavectordatastore.controller;

import org.springframework.ai.document.Document;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@RestController
public class AIController {

    @Value("classpath:input.txt")
    Resource resource;

    private final VectorStore vectorStore;

    public AIController(VectorStore vectorStore){
        this.vectorStore = vectorStore;
    }
    @GetMapping("/load")
    public String load() throws IOException, InterruptedException {

        List<Document> documents = Files.lines(resource.getFile().toPath())
                .map(Document::new)
                .toList();

        TextSplitter textSplitter = new TokenTextSplitter();
        for(Document document: documents){
            System.out.println("Before split"+document);
            List<Document> splitDocuments = textSplitter.split(document);
            System.out.println("After split"+splitDocuments);
            vectorStore.add(splitDocuments);
           // Thread.sleep(610000);
        }

        return "loaded: " + resource.getFilename();
    }

    @GetMapping("/search")
    public String search(){
        List<Document> result = vectorStore.similaritySearch(SearchRequest
                .builder()
                .query("classic novel about health and society")
                .topK(3).build());
    return result.toString();
    }
}
