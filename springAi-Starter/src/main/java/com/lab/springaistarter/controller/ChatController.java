package com.lab.springaistarter.controller;

import com.lab.springaistarter.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class ChatController {

    @Autowired
    ChatService chatService;

    @GetMapping("chat/{prompt}")
    public String getResponse(@PathVariable String prompt){
        return chatService.generateResponse(prompt);
}
}
