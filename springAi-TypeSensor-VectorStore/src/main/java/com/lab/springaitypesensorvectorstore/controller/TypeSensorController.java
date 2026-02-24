package com.lab.springaitypesensorvectorstore.controller;

import java.util.List;

import com.lab.springaitypesensorvectorstore.service.TypeSensorService;
import org.springframework.ai.document.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TypeSensorController {

    @Autowired
    TypeSensorService typeSensorService;

    @GetMapping("/load")
    public void load(){
        typeSensorService.load();
    }

    @GetMapping("/search")
    public List<Document> search(@RequestParam(value="query", defaultValue = "technology") String query){

        return typeSensorService.search(query);//.stream().map(Document::getText).collect(Collectors.toList());
    }
}
