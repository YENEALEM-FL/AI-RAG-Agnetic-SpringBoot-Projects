package com.lab.springaiextractiontransformlaod.controller;

import com.lab.springaiextractiontransformlaod.service.*;
import org.springframework.ai.document.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ETLController {

    @Autowired
    ETLReaderService etlReaderService;

    @Autowired
    ETLTransformerService etlTransformerService;

    @Autowired
    ETLWriterService etlWriterService;

    @Autowired
    ETLPdfReaderService etlPdfReaderService;

    @Autowired
    ETLJsonReaderService etlJsonReaderService;

    @Autowired
    ETLMdReaderService etlMdReaderService;

    @Autowired
    ETLMdTransformService etlMdTransformService;

    @GetMapping("/etl")
    public String doExtractionTransformingLoading(){

        List<Document> documents = etlReaderService.readInputData();
        List<Document> documentList = etlTransformerService.transform(documents);
        etlWriterService.writeTextFile(documentList);
        return "ETL Completed!";
    }

    @GetMapping("/etl-for-pdf")
    public String doPdfExtractionTransformingLoading(){

        List<Document> documents = etlPdfReaderService.readPdfData();
        List<Document> documentList = etlTransformerService.transform(documents);
        etlWriterService.writeTextFile(documentList);
        return "ETL PDF pipeline Completed!";
    }

    @GetMapping("/etl-for-json")
    public String doJsonExtractionTransformingLoading(){

        List<Document> documents = etlJsonReaderService.readDocumentsFromJson();
        List<Document> documentList = etlTransformerService.transform(documents);
        etlWriterService.writeTextFile(documentList);
        return "ETL Json pipeline Completed!";
    }


    @GetMapping("/etl-for-md")
    public String doMdExtractionTransformingLoading(){

        List<Document> documents = etlMdReaderService.readMarkdownData();
        List<Document> documentList = etlMdTransformService.transformMdData(documents);
        etlWriterService.writeTextFile(documentList);
        return "ETL MD pipeline Completed!";
    }


}
