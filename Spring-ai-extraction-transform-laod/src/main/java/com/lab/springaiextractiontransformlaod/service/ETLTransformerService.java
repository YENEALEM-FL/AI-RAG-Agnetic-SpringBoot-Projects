package com.lab.springaiextractiontransformlaod.service;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.document.Document;
import org.springframework.ai.model.transformer.KeywordMetadataEnricher;
import org.springframework.ai.model.transformer.SummaryMetadataEnricher;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ETLTransformerService {

    @Autowired
    ChatModel chatModel;

    public List<Document> transform(List<Document> documents) {

        TextSplitter textSplitter = new TokenTextSplitter();

        List<Document> documentList = textSplitter.split(documents);

//        KeywordMetadataEnricher keywordMetadataEnricher = new KeywordMetadataEnricher(chatModel,5);
//
//        return keywordMetadataEnricher.apply(documentList);

        SummaryMetadataEnricher summaryMetadataEnricher = new SummaryMetadataEnricher(chatModel,
                List.of(SummaryMetadataEnricher.SummaryType.PREVIOUS,
                        SummaryMetadataEnricher.SummaryType.CURRENT,
                        SummaryMetadataEnricher.SummaryType.NEXT));

        return summaryMetadataEnricher.apply(documentList);
    }

    public List<Document> transformPdfFile(List<Document> documents) {

        TextSplitter textSplitter = new TokenTextSplitter();

        List<Document> documentList = textSplitter.split(documents);

//        KeywordMetadataEnricher keywordMetadataEnricher = new KeywordMetadataEnricher(chatModel,5);
//
//        return keywordMetadataEnricher.apply(documentList);

        SummaryMetadataEnricher summaryMetadataEnricher = new SummaryMetadataEnricher(chatModel,
                List.of(SummaryMetadataEnricher.SummaryType.PREVIOUS,
                        SummaryMetadataEnricher.SummaryType.CURRENT,
                        SummaryMetadataEnricher.SummaryType.NEXT));

        return summaryMetadataEnricher.apply(documentList);
    }


}
