package com.lab.springaitoolcallingproject.controller;

import java.util.Map;

import com.lab.springaitoolcallingproject.tools.SmartHomeTools;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
public class ChatController {
    
    private final ChatClient chatClient;
    
    @Autowired
    private SmartHomeTools smartHomeTools;
    
    public ChatController(ChatModel chatModel) {
        this.chatClient = ChatClient.create(chatModel);
    }
    
    @PostMapping("/message")
    public Map<String, Object> sendMessage(@RequestBody Map<String, String> request) {
        try {
            String userMessage = request.get("message");
            String userId = request.getOrDefault("userId", "default-user");
            
            if (userMessage == null || userMessage.trim().isEmpty()) {
                return Map.of("success", false, "error", "Message cannot be empty");
            }
            
            String response = chatClient
                .prompt(userMessage)
                .tools(smartHomeTools)
                .call()
                .content();
            
            return Map.of(
                "success", true,
                "response", response,
                "userId", userId
            );
            
        } catch (Exception e) {
            return Map.of(
                "success", false,
                "error", "Error processing message: " + e.getMessage()
            );
        }
    }
    
    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of("status", "OK", "service", "Smart Home Chat Controller");
    }
}