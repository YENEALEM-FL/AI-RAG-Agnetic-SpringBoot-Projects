package com.lab.springbootstructuredoutput.controller;

import com.lab.springbootstructuredoutput.Movie;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AIController {

    ChatClient chatClient;

    public AIController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }


    @GetMapping("/director-movie-service")
    public List<Movie> getDirectorMoviesInPojo(@RequestParam("directorName") String directorName) {
        String template = """
                "Generate a list of movies directed by {directorName}. If the director is unknown, return null. 
                Each movie should include a title and release year. {format}"
                """;
        return chatClient.prompt()
                .user(userSpec -> userSpec.text(template)
                        .param("directorName", directorName)
                        .param("format", "json")
                )
                .call()
                .entity(new ParameterizedTypeReference<List<Movie>>() {
                });
    }
}
