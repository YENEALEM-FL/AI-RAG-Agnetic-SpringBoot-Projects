package com.lab.springaisecretekeyofazure.controller;

import java.util.Objects;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AIChatController {

    ChatClient chatClient;

    public AIChatController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping("/chat")
    public String chat(@RequestParam(value="message", defaultValue = "What is the capital city of France?") String message) {

        return Objects.requireNonNull(chatClient.prompt()
                        .user(message)
                        .call()
                        .chatResponse())
                        .getResult()
                .getOutput()
                .getText();
    }
}
