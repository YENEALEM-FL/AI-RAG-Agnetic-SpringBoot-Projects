package com.lab.springaimetadatapgvector.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.lab.springaimetadatapgvector.service.AiService;
import org.springframework.ai.document.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AiController {

    @Autowired
    AiService aiService;

    @GetMapping("/load")
    public void load() throws IOException {
        aiService.loadData();
    }

    @GetMapping("/search")
    public List<String> search(@RequestParam(value="query", defaultValue = "technology") String query){

        List<Document> docs = aiService.search(query);//

        return docs.stream().map(Document::getText).collect(Collectors.toList());
    }
}
