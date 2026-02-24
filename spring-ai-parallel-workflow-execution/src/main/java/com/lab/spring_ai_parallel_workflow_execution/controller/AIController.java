package com.lab.spring_ai_parallel_workflow_execution.controller;

import com.lab.spring_ai_parallel_workflow_execution.services.ParallelWorkflowService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AIController {

    String coreMessage = "Introducing our innovative new eco-friendly product that will revolutionize your daily routine!";

    @Autowired
    private ParallelWorkflowService workflowService;

    public List<String> platformInstructions = List.of("""
                Generate a tweet for Twitter based on the core message.
                Keep it concise (under 280 characters), engaging, and include relevant hashtags.
                """,
                """
                Create a Facebook post based on the core message.
                It can be a bit longer, more descriptive, and encourage user interaction (e.g., ask a question).
                """,
                """
                Write an Instagram caption based on the core message.
                Focus on visual appeal and use relevant hashtags. Keep it relatively short and impactful.
                """,
                """
                Develop a LinkedIn post based on the core message.
                Maintain a professional tone, highlight the benefits for professionals, and consider including a call to action to learn more.
                """
                );

    @GetMapping("/parallel")
    public List<String> platformParallelExecution() {

        String promptTemplate = """

            Adapt the following core marketing message for the specified social media platform,
            following the specific instructions provided.
            Core Message
            %s
            """;
        String prompt = String.format(promptTemplate, coreMessage);

        List<String> parallelResponses = workflowService
                .parallel(prompt,
                        platformInstructions,
                        3);

    System.out.println("\nGenerated Social Media Posts:");

    for (int i = 0; i< parallelResponses.size(); i++){
        System.out.println("Platforms "+ (i + 1) + ":\n"+parallelResponses.get(i) + "\n...");

    }
    return parallelResponses;
    }
  }
