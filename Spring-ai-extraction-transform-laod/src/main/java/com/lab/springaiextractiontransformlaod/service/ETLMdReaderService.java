package com.lab.springaiextractiontransformlaod.service;

import org.springframework.ai.document.Document;
import org.springframework.ai.reader.markdown.MarkdownDocumentReader;
import org.springframework.ai.reader.markdown.config.MarkdownDocumentReaderConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ETLMdReaderService {

    @Value("classpath:sample_markdown_data.md")
    Resource resource;

    public List<Document> readMarkdownData() {

        MarkdownDocumentReaderConfig markdownDocumentReaderConfig = MarkdownDocumentReaderConfig
                .builder()
                .withHorizontalRuleCreateDocument(true)
                .withIncludeCodeBlock(false)
                .withAdditionalMetadata("filename", Objects.requireNonNull(resource.getFilename()))
                .build();

        MarkdownDocumentReader markdownDocumentReader = new MarkdownDocumentReader(resource, markdownDocumentReaderConfig);

        return markdownDocumentReader.get();
    }
}
