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

    @GetMapping("/search-using-comparison")
    public List<String> searchUsingComparison(@RequestParam(value="query", defaultValue = "technology") String query){

        List<Document> docs = aiService.searchUsingComparison(query);//

        return docs.stream().map(Document::getFormattedContent).collect(Collectors.toList());
    }

    @GetMapping("/search-using-in-and-nin")
    public List<String> searchUsingInAndNin(@RequestParam(value="query", defaultValue = "technology") String query){

        List<Document> docs = aiService.searchUsingInAndNin(query);//

        return docs.stream().map(Document::getFormattedContent).collect(Collectors.toList());
    }

    @GetMapping("/search-not")
    public List<String> searchUsingNot(@RequestParam(value="query", defaultValue = "technology") String query){

        List<Document> docs = aiService.searchUsingNot(query);//

        return docs.stream().map(Document::getFormattedContent).collect(Collectors.toList());
    }
}
