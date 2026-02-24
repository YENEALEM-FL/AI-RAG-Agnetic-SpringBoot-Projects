package com.lab.springaiextractiontransformlaod.service;

import org.springframework.ai.document.Document;
import org.springframework.ai.reader.JsonReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ETLJsonReaderService {

    @Value("classpath:sample_json_data.json")
    Resource resource;


    public List<Document> readDocumentsFromJson() {

        JsonReader reader = new JsonReader(resource,"name","gender");

        return reader.get();
    }
}
