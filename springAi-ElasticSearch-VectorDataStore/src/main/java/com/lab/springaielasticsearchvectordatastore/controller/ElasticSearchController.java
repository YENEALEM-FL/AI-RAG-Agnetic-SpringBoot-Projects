package com.lab.springaielasticsearchvectordatastore.controller;

import java.io.IOException;
import java.util.List;

import com.lab.springaielasticsearchvectordatastore.service.ElasticSearchService;
import org.springframework.ai.document.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ElasticSearchController {

    @Autowired
    ElasticSearchService elasticSearchService;

    @GetMapping("/load")
    public void load() throws IOException {
        elasticSearchService.load();
    }

    @GetMapping("/search")
    public List<Document> search(@RequestParam(value="query", defaultValue = "technology") String query){

        return elasticSearchService.search(query);//.stream().map(Document::getText).collect(Collectors.toList());
    }
}
