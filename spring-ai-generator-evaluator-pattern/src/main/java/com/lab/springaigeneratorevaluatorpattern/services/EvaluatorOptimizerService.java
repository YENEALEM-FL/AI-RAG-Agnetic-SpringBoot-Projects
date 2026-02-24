package com.lab.springaigeneratorevaluatorpattern.services;

import com.lab.springaigeneratorevaluatorpattern.model.Evaluation;
import com.lab.springaigeneratorevaluatorpattern.model.EvaluationResponse;
import com.lab.springaigeneratorevaluatorpattern.model.Generation;
import com.lab.springaigeneratorevaluatorpattern.model.RefinedResponse;
import org.springframework.ai.chat.client.ChatClient;

import java.util.ArrayList;
import java.util.List;

import static com.lab.springaigeneratorevaluatorpattern.contants.Constants.DEFAULT_EVALUATOR_PROMPT_MOVIE_REVIEW;
import static com.lab.springaigeneratorevaluatorpattern.contants.Constants.DEFAULT_GENERATOR_PROMPT_MOVIE_REVIEW;

public class EvaluatorOptimizerService {

    private final ChatClient chatClient;

    private final String generatorPrompt;

    private final String evaluatorPrompt;

    public EvaluatorOptimizerService(ChatClient chatClient) {
        this(chatClient, DEFAULT_GENERATOR_PROMPT_MOVIE_REVIEW,
                DEFAULT_EVALUATOR_PROMPT_MOVIE_REVIEW);
    }

    public EvaluatorOptimizerService(ChatClient chatClient, String generatorPrompt, String evaluatorPrompt) {

        this.chatClient = chatClient;
        this.generatorPrompt = generatorPrompt;
        this.evaluatorPrompt = evaluatorPrompt;
    }

    public RefinedResponse loop(String task) {
        List<String> memory = new ArrayList<>();
        List<Generation> chainOfThought = new ArrayList<>();
        return loop(task, "", memory, chainOfThought);
    }

    private RefinedResponse loop(String task, String context, List<String> memory,
                                 List<Generation> chainOfThought) {

        Generation generation = generate(task, context);
        memory.add(generation.response());
        chainOfThought.add(generation);

        EvaluationResponse evaluationResponse = evaluate(generation.response(), task);

        if (evaluationResponse.evaluation().equals(Evaluation.PASS)) {
            // Solution is accepted!
            return new RefinedResponse(generation.response(), chainOfThought);
        }

        // Accumulated new context including the last and the previous attempts and
        // feedbacks.
        StringBuilder newContext = new StringBuilder();
        newContext.append("Previous attempts:");
        for (String m : memory) {
            newContext.append("\n- ").append(m);
        }
        newContext.append("\nFeedback: ").append(evaluationResponse.feedback());

        return loop(task, newContext.toString(), memory, chainOfThought);
    }

    private Generation generate(String task, String context) {
        Generation generationResponse = chatClient.prompt()
                .user(u -> u.text("{prompt}\n{context}\nTask: {task}")
                        .param("prompt", this.generatorPrompt)
                        .param("context", context)
                        .param("task", task))
                .call()
                .entity(Generation.class);
        System.out.println("generationResponse"+ generationResponse);

        assert generationResponse != null;
        System.out.printf("\n=== GENERATOR OUTPUT ===\nTHOUGHTS: %s\n\nRESPONSE:\n %s\n%n",
                generationResponse.thoughts(), generationResponse.response());
        return generationResponse;
    }


    private EvaluationResponse evaluate(String content, String task) {

        EvaluationResponse evaluationResponse = chatClient.prompt()
                .user(u -> u.text("{prompt}\nOriginal task: {task}\nContent to evaluate: {content}")
                        .param("prompt", this.evaluatorPrompt)
                        .param("task", task)
                        .param("content", content))
                .call()
                .entity(EvaluationResponse.class);

        assert evaluationResponse != null;
        System.out.printf("\n=== EVALUATOR OUTPUT ===\nEVALUATION: %s\n\nFEEDBACK: %s\n%n",
                evaluationResponse.evaluation(), evaluationResponse.feedback());
        return evaluationResponse;
    }

}
