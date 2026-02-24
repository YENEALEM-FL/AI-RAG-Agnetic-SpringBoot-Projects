package com.lab.springaiextractiontransformlaod.service;

import org.springframework.ai.document.Document;
import org.springframework.ai.document.MetadataMode;
import org.springframework.ai.writer.FileDocumentWriter;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ETLWriterService {

    public void writeTextFile(List<Document> documents){

        FileDocumentWriter fdw = new FileDocumentWriter("output.txt", false, MetadataMode.ALL,true);
        fdw.write(documents);
    }
}
