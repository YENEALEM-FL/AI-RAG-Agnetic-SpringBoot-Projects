package com.lab.springaiweaviatevectorstore.controller;

import com.lab.springaiweaviatevectorstore.service.WeaviateService;
import org.springframework.ai.document.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class TypeSensorController {

    @Autowired
    WeaviateService weaviateService;

    @GetMapping("/load")
    public void load() throws IOException {
        weaviateService.load();
    }

    @GetMapping("/search")
    public List<Document> search(@RequestParam(value="query", defaultValue = "technology") String query){

        return weaviateService.search(query);//.stream().map(Document::getText).collect(Collectors.toList());
    }
}
