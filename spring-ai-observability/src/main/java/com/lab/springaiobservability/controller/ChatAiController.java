package com.lab.springaiobservability.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class ChatAiController {

    ChatClient chatClient;

    public ChatAiController(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }

    @GetMapping("/chat")
    public String chat(@RequestParam(value = "message", defaultValue = "Give me a best book to know about world history!") String message) {

        return Objects.requireNonNull(chatClient.prompt()
                        .user(message)
                        .call()
                        .chatResponse())
                .getResult()
                .getOutput()
                .toString();
    }
}
