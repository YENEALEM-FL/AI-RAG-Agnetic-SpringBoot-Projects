package com.lab.springaiollamamodel.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class AppController {
    ChatClient chatClient;

    public AppController(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }

    @GetMapping("/chat")
    public String getResponse(@RequestParam(value = "message", defaultValue = "What is the capital city of US?") String message) {

        return Objects.requireNonNull(chatClient.prompt(message)
                        .call()
                        .chatResponse())
                .getResult()
                .getOutput()
                .getText();
    }
}
