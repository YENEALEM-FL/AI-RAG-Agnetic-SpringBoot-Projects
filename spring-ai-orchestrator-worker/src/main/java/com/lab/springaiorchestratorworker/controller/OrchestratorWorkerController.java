package com.lab.springaiorchestratorworker.controller;


import com.lab.springaiorchestratorworker.Services.OrchestratorWorkers;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrchestratorWorkerController {


    ChatClient chatClient;

    public OrchestratorWorkerController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @PostMapping("/orchestrator")
    public OrchestratorWorkers.FinalResponse processTask(@RequestBody String taskDescription) {
        return new OrchestratorWorkers(chatClient).process(taskDescription);
    }


}
