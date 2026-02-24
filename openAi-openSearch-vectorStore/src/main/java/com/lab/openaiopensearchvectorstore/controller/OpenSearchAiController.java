package com.lab.openaiopensearchvectorstore.controller;

import com.lab.openaiopensearchvectorstore.service.OpenSearchAiService;
import java.io.IOException;
import java.util.List;
import org.springframework.ai.document.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OpenSearchAiController {

    @Autowired
    OpenSearchAiService openSearchAiService;

    @GetMapping("/load")
    public void load() throws IOException {
        openSearchAiService.load();
    }

    @GetMapping("/search")
    public List<Document> search(@RequestParam(value="query", defaultValue = "technology") String query){

        return openSearchAiService.search(query);//.stream().map(Document::getText).collect(Collectors.toList());
    }
}
