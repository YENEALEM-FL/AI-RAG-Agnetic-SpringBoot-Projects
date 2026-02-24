package com.lab.springaistarter.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ChatService {

    ChatClient chatClient;
    public ChatService(ChatClient.Builder chatService){
        this.chatClient = chatService.build();
    }
    public String generateResponse(String prompt){

        return Objects.requireNonNull(chatClient
                        .prompt(prompt)
                        .call()
                        .chatResponse())
                .getResult()
                .getOutput()
                .getText();


    }

}
