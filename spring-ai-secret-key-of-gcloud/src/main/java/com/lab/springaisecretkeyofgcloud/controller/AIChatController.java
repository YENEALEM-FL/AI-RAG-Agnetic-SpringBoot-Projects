package com.lab.springaisecretkeyofgcloud.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/api")
public class AIChatController {

    ChatClient chatClient;

    public AIChatController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping("/chat")
    public String chat(@RequestParam(value="message", defaultValue = "what is the capital city of Japan?") String message) {

        return Objects.requireNonNull(chatClient.prompt()
                        .user(message)
                        .call()
                        .chatResponse())
                        .getResult()
                .getOutput()
                .getText();
    }
}
