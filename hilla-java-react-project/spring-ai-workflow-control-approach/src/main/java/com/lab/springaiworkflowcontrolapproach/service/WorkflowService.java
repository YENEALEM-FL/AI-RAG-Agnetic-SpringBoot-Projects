package com.lab.springaiworkflowcontrolapproach.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class WorkflowService {

    ChatClient chatClient;

    public WorkflowService(ChatClient.Builder chatClientBuilder){
        this.chatClient = chatClientBuilder.build();
    }

    String [] inputs = {
            "Summerize the following text in 2-3 words",
            "Extract 3-5 key points from the text as bullet points",
            "generate three thoughtful follow up question about the content"
    };
    public String chatServiceInWorkflow(String input){

        String response = input;

            for(String prompt : inputs){
                 input = String.format("{%s}\n {%s}", prompt, response);
                response = chatClient.prompt(input).call().content();
            }
        return response;
    }
}
