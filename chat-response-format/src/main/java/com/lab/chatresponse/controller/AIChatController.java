package com.lab.chatresponse.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class AIChatController {

    private final ChatClient chatClient;

    public AIChatController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping("/chat")
    public String chat(@RequestParam(value="message", defaultValue="Give me java hello world program ?") String message) {
        ChatResponse chatResponse = getMessage(message);
        return chatResponse.getResult().getOutput().getText()  ;
    }


    public ChatResponse getMessage(String message) {
        ChatResponse chatResponse = chatClient.prompt()
                .user(message)
                .call()
                .chatResponse();
        return chatResponse;
    }

    @GetMapping("/chatjson")
    public ChatResponse chatJson(@RequestParam(value="message", defaultValue="Please give me a simple joke in one line ") String message) {
        ChatResponse chatResponse= chatClient.prompt()
                .user(message)
                .call()
                .chatResponse();
        System.out.println("Chat Response: " + chatResponse);
        return chatResponse;
    }

}
