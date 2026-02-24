package com.lab.springaiextractiontransformlaod.service;

import org.springframework.ai.document.Document;
import org.springframework.ai.reader.ExtractedTextFormatter;
import org.springframework.ai.reader.TextReader;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.reader.pdf.config.PdfDocumentReaderConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ETLPdfReaderService {

    @Value("classpath:sample_local_pdf.pdf")
    Resource resource;

    public List<Document> readPdfData() {

        PagePdfDocumentReader pagePdfDocumentReader = new PagePdfDocumentReader(resource, PdfDocumentReaderConfig
                .builder()
                .withPageTopMargin(0)
                .withPagesPerDocument(1)
                .withPageBottomMargin(0)
                .withPageExtractedTextFormatter(ExtractedTextFormatter
                        .builder()
                        .withNumberOfBottomTextLinesToDelete(0).build())
                .withPagesPerDocument(1)
                .build());
        return pagePdfDocumentReader.read();
    }

}
