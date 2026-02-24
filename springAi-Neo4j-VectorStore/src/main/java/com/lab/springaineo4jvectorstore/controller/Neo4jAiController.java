package com.lab.springaineo4jvectorstore.controller;

import com.lab.springaineo4jvectorstore.service.Neo4jService;
import org.springframework.ai.document.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Neo4jAiController {

    @Autowired
    Neo4jService neo4jService;

    @GetMapping("/load")
    public void load(){
        neo4jService.load();
    }

    @GetMapping("/search")
    public List<Document> search(@RequestParam(value="query", defaultValue = "technology") String query){

        return neo4jService.search(query);
    }
}
