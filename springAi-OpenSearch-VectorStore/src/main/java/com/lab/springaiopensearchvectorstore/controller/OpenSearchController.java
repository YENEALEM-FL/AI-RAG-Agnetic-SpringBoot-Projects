package com.lab.springaiopensearchvectorstore.controller;

import com.lab.springaiopensearchvectorstore.service.OpenSearchService;
import java.io.IOException;
import java.util.List;
import org.springframework.ai.document.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OpenSearchController {

    @Autowired
    OpenSearchService openSearchService;

    @GetMapping("/load")
    public void load() throws IOException {
        openSearchService.load();
    }

    @GetMapping("/search")
    public List<Document> search(@RequestParam(value="query", defaultValue = "technology") String query){

        return openSearchService.search(query);//.stream().map(Document::getText).collect(Collectors.toList());
    }
}
