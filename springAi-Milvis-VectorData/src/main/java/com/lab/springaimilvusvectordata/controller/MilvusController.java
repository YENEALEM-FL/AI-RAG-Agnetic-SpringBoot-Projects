package com.lab.springaimilvusvectordata.controller;

import com.lab.springaimilvusvectordata.service.MilvusService;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.ai.document.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MilvusController {

    @Autowired
    MilvusService milvusService;

    @GetMapping("/load")
    public void load(){
        milvusService.load();
    }

    @GetMapping("/search")
    public List<Document> search(@RequestParam(value="query", defaultValue = "technology") String query){

        return milvusService.search(query);//.stream().map(Document::getText).collect(Collectors.toList());
    }
}
