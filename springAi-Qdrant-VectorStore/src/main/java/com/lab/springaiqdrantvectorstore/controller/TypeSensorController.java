package com.lab.springaiqdrantvectorstore.controller;

import com.lab.springaiqdrantvectorstore.service.QdrantService;
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
    QdrantService qdrantService;

    @GetMapping("/load")
    public void load() throws IOException {
        qdrantService.load();
    }

    @GetMapping("/search")
    public List<Document> search(@RequestParam(value="query", defaultValue = "technology") String query){

        return qdrantService.search(query);//.stream().map(Document::getText).collect(Collectors.toList());
    }
}
