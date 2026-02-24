package com.lab.modelservicecontrollerspringai.service;

import com.lab.modelservicecontrollerspringai.model.Answer;
import com.lab.modelservicecontrollerspringai.model.Question;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.stereotype.Service;

@Service
public class GetRecipeGeneratorService implements GetRecipeGenerator {

    private final ChatClient chatClient;

    public GetRecipeGeneratorService(ChatClient.Builder chatClientBuilder){
        this.chatClient = chatClientBuilder.build();
    }
    @Override
    public Answer generateRecipe(Question question) {
    return new Answer(getMessage(question.getQuestion()).getResult().getOutput().getText());
    }

    public ChatResponse getMessage(String message){
        return chatClient.prompt().user(message).call().chatResponse();
    }
}
