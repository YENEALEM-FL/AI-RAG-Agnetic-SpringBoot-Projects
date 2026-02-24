package com.lab.resourceTemplateExample.service;

import com.lab.resourceTemplateExample.model.Answer;
import com.lab.resourceTemplateExample.model.Question;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.stereotype.Service;

@Service
public class GetRecipeGeneratorService implements GetRecipeGenerator {

    private final ChatClient chatClient;
    private final String recipeTemplate  = """
        Answer for {foodName} for {question}""";

    public GetRecipeGeneratorService(ChatClient.Builder chatClientBuilder){
        this.chatClient = chatClientBuilder.build();
    }
    @Override
    public Answer generateRecipe(Question question) {
    return new Answer(getMessage(question).getResult().getOutput().getText());
    }

    public ChatResponse getMessage(Question question){
        return chatClient.prompt()
                .user(userSpec -> userSpec.text(recipeTemplate)
                        .param("foodName",question.getFoodName())
                        .param("question",question.getQuestion()))
                .call()
                .chatResponse();
    }
}
