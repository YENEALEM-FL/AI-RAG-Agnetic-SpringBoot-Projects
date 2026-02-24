package com.lab.springaiappachecassandravectorstore.controller;

import com.lab.springaiappachecassandravectorstore.service.AICassandraVectorService;
import org.springframework.ai.document.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AIController {
    @Autowired
    AICassandraVectorService aiCassandraVectorService;

    @GetMapping("/load")
    public void loadDocuments(){
        aiCassandraVectorService.getDocuments();
    }

    @GetMapping("/search")
    public List<Document> searchDocuments(){

        return aiCassandraVectorService.searchDocuments("Technology").stream().toList();
    }


}
