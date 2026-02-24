package com.lab.springaioracle23ia.controller;

import java.util.List;

import com.lab.springaioracle23ia.service.OracleAiService;
import org.springframework.ai.document.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OracleAiController {

    @Autowired
    OracleAiService oracleAiService;

    @GetMapping("/load")
    public void load(){
        oracleAiService.load();
    }

    @GetMapping("/search")
    public List<String> search(@RequestParam(value="query", defaultValue = "technology") String query){

        return oracleAiService.search(query);
    }
}
