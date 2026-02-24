package com.lab.springaiextractiontransformlaod.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.model.transformer.KeywordMetadataEnricher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class ETLMdTransformService {

    private static final Logger logger = LoggerFactory.getLogger(ETLMdTransformService.class);

    @Autowired
    ChatModel chatModel;

    public List<Document> transformMdData(List<Document> documents) {

        return apply(documents);
    }

    private List<Document> apply(List<Document> documents) {

        for(Document document: documents){
           var template = new PromptTemplate(String.format(KeywordMetadataEnricher.KEYWORDS_TEMPLATE, 5));
           Prompt prompt = template.create(Map.of(KeywordMetadataEnricher.CONTEXT_STR_PLACEHOLDER, document.getContentFormatter()));

           try{
               TimeUnit.SECONDS.sleep(20);
           } catch (InterruptedException e) {
               throw new RuntimeException(e);
           }

           String keywords = this.chatModel.call(prompt).getResult().getOutput().getText();
           logger.info("Extracted Keywords: {}",keywords);
            assert keywords != null;
            document.getMetadata().put(KeywordMetadataEnricher.CONTEXT_STR_PLACEHOLDER, keywords);
        }
        return documents;
    }

}
