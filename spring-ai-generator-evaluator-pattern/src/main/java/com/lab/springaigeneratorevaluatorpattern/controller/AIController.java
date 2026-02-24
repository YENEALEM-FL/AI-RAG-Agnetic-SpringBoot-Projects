package com.lab.springaigeneratorevaluatorpattern.controller;

import com.lab.springaigeneratorevaluatorpattern.model.RefinedResponse;
import com.lab.springaigeneratorevaluatorpattern.services.EvaluatorOptimizerService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AIController {

    ChatClient chatClient;

    public AIController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }


    @PostMapping("/process")
    public RefinedResponse processInput(@RequestBody String userInput) {
        RefinedResponse refinedResponse = new EvaluatorOptimizerService(chatClient).loop("""
                <user input>
                """ + userInput + """
                </user input>
                """);
        System.out.println("FINAL OUTPUT:\n : " + refinedResponse);
        return refinedResponse;
    }

}
