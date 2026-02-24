package com.lab.springaistructuredoutput.controller;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.converter.ListOutputConverter;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController()
public class AIController {
    private final ChatClient chatClient;

    public AIController(ChatClient.Builder chatClient){
        this.chatClient = chatClient.build();
    }

    // get raw data

    @GetMapping("/chat")
    public String getMessage(@RequestParam(value = "message", defaultValue = "Give me the names of the five states in the US.") String message){

        return chatClient.prompt()
                .user(message)
                .call()
                .chatResponse()
                .getResult()
                .getOutput()
                .getText();
    }

    // get a list of data
    @GetMapping("/chatList")
    public List<String> getMessagesAsList(@RequestParam(value = "message", defaultValue = "Give me the names of the five states in the US.") String message){

    return chatClient
        .prompt()
        .user(message)
        .call()
        .entity(new ListOutputConverter(new DefaultConversionService()));
    }

    // get a map of data. The key and the value of the map are taken directly from the response.
    @GetMapping("/chatMap")
    public Map<String, String> getMessagesAsMap(@RequestParam(value = "message", defaultValue = "Give me the names of the five states and their capitals in the US.") String message){

        return chatClient
                .prompt()
                .user(message)
                .call()
                .entity(new ParameterizedTypeReference<Map<String, String>>(){});
    }
}
