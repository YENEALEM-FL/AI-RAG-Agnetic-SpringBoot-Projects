package com.lab.spring_ai_parallel_workflow_execution.services;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class ParallelWorkflowService {

    ChatClient chatClient;

    public ParallelWorkflowService(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }

    public List<String> parallel(String prompt, List<String> inputs, int workers){

        ExecutorService executor = Executors.newFixedThreadPool(workers);

        try{
        List<CompletableFuture<String>> futures = inputs.stream()
                .map(input -> CompletableFuture.supplyAsync(()->
                {
                    try{
                        return chatClient.prompt(prompt + "\n Input: "+ input).call().content();

                    } catch(Exception e){
                        throw new RuntimeException("Failed to process input: "+input, e);
                    }
                }, executor))
                .toList();

        CompletableFuture<Void> allfutures = CompletableFuture.allOf(futures.toArray(CompletableFuture[]:: new));

        allfutures.join();

        return futures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
        }
        finally{
            executor.shutdown();
        }
    }
}
