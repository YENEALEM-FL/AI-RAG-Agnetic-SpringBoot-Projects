package com.lab.resourceTemplateExample.controller;

import com.lab.resourceTemplateExample.service.MovieService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {

    @Autowired
    private MovieService movieService;

    private final ChatClient chatClient;

    public MovieController(ChatClient.Builder chatClinetBuilder) {
        this.chatClient = chatClinetBuilder.build();
    }

    @GetMapping("/movieinfo")
    public String movieInfo(@RequestParam(value="movieName",
            defaultValue="The Matrix") String movieName){
        return movieService.movieInfo(movieName);
    }

}
