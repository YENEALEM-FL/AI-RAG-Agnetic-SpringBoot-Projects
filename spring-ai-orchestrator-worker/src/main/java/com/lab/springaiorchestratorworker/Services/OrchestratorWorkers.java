package com.lab.springaiorchestratorworker.Services;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.util.Assert;

import java.util.List;

import static com.lab.springaiorchestratorworker.constants.Constants.DEFAULT_ORCHESTRATOR_PROMPT;
import static com.lab.springaiorchestratorworker.constants.Constants.DEFAULT_WORKER_PROMPT;


public class OrchestratorWorkers {

    private final ChatClient chatClient;
    private final String orchestratorPrompt;
    private final String workerPrompt;

    public record Task(String type, String description) { }
    public record OrchestratorResponse(String analysis, List<Task> tasks) { }
    public record FinalResponse(String analysis, List<String> workerResponses) { }

    public OrchestratorWorkers(ChatClient chatClient) {
        this(chatClient, DEFAULT_ORCHESTRATOR_PROMPT, DEFAULT_WORKER_PROMPT);
    }

    public OrchestratorWorkers(ChatClient chatClient, String orchestratorPrompt, String workerPrompt) {
        Assert.notNull(chatClient, "ChatClient must not be null");
        Assert.hasText(orchestratorPrompt, "Orchestrator prompt must not be empty");
        Assert.hasText(workerPrompt, "Worker prompt must not be empty");

        this.chatClient = chatClient;
        this.orchestratorPrompt = orchestratorPrompt;
        this.workerPrompt = workerPrompt;
    }

    @SuppressWarnings("null")
    public FinalResponse process(String taskDescription) {
        Assert.hasText(taskDescription, "Task description must not be empty");

        // Step 1: Get orchestrator response
        OrchestratorResponse orchestratorResponse = this.chatClient.prompt()
                .user(u -> u.text(this.orchestratorPrompt)
                        .param("task", taskDescription))
                .call()
                .entity(OrchestratorResponse.class);

        assert orchestratorResponse != null;
        System.out.printf("\n=== ORCHESTRATOR OUTPUT ===\nANALYSIS: %s\n\nTASKS: %s\n%n",
                orchestratorResponse.analysis(), orchestratorResponse.tasks());

        // Step 2: Process each task
        List<String> workerResponses = orchestratorResponse.tasks().stream().map(task -> this.chatClient
                .prompt()
                .user(u -> u.text(this.workerPrompt)
                        .param("original_task", taskDescription)
                        .param("task_type", task.type())
                        .param("task_description", task.description()))
                .call()
                .content()).toList();

        System.out.println("\n=== WORKER OUTPUT ===\n" + workerResponses);

        return new FinalResponse(orchestratorResponse.analysis(), workerResponses);
    }

}