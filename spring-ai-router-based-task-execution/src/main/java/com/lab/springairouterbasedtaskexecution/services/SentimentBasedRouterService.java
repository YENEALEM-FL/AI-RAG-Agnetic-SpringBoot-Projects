package com.lab.springairouterbasedtaskexecution.services;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SentimentBasedRouterService {

    private ChatClient chatClient;

    @Autowired
    PositiveFeedbackHandler positiveFeedbackHandler;
    @Autowired
    NegativeFeedbackHandler negativeFeedbackHandler;
    @Autowired
    NeutralFeedbackHandler neutralFeedbackHandler;

    public SentimentBasedRouterService(ChatClient.Builder chatClientBuilder) {

        this.chatClient = chatClientBuilder.build();
    }

    public static final PromptTemplate sentimentPrompt = new PromptTemplate("""
        Analyze the sentiment of the following user feedback,
        The possible sentiments are: 'positive', 'negative', 'neutral'
        User FeedBack: {feedBack}
        
        Respond with the identified sentiment only.
        """);

    public void routeFeedBack(String feedBack) {

        System.out.println("Routing Feedback: " + feedBack);

        Message message = sentimentPrompt.createMessage(Map.of("feedBack", feedBack));
        Prompt prompt = new Prompt(List.of(message));
        System.out.println("Prompt: " + prompt);
        var response = chatClient.prompt(prompt).call().content();
        assert response != null;
        var sentiment = response.trim().toLowerCase();

        System.out.println("Identified Sentiment: " + sentiment);
        switch (sentiment) {
            case "positive":
                positiveFeedbackHandler.handle(feedBack);
                break;
            case "negative":
                negativeFeedbackHandler.handle(feedBack);
                break;
            case "neutral":
                neutralFeedbackHandler.handle(feedBack);
                break;
            default:
                System.out.println("Could not determine sentiment for: " + feedBack);
                break;
    }
  }
}

@Service
class PositiveFeedbackHandler{

    public void handle(String message){
    System.out.println("positive feedback: "+message);
    }

}

@Service
class NegativeFeedbackHandler{
    public void handle(String message){
        System.out.println("negative feedback: "+ message);
    }
}


@Service
class NeutralFeedbackHandler{
    public void handle(String message){
        System.out.println("neutral feedback: "+ message);
    }
}
