package com.lab.springailmstudio.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lab.springailmstudio.model.ChatRequest;
import com.lab.springailmstudio.model.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class AIController {

    private final RestTemplate restTemplate;

    @Value("${external.ai.api.models}")
    private String modelUrl;

    @Value("${external.ai.api.completions}")
    private String chatCompletionUrl;

    public AIController(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @GetMapping("/models")
    public ResponseEntity<String> getModels(){

        ResponseEntity<String> response = restTemplate.getForEntity(modelUrl, String.class);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    @PostMapping("/chat")
    public ResponseEntity<String> sendChatRequest(@RequestBody ChatRequest chatRequest) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<ChatRequest> requestHttpEntity = new HttpEntity<>(chatRequest, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                chatCompletionUrl,
                HttpMethod.POST,
                requestHttpEntity,
                String.class);

        System.out.println(response.getBody());
        ObjectMapper objectMapper = new ObjectMapper();

        ChatResponse chatResponse = objectMapper.readValue(response.getBody(), ChatResponse.class);
        return ResponseEntity.status(response.getStatusCode()).body(chatResponse.toString());
    }
}
