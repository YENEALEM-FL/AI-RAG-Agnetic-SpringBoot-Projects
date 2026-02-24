package com.lab.resourceTemplateExample.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
public class MovieService implements MovieActors{

    private final ChatClient chatClient;

    @Value("classpath:prompt/movie-info.st")
    Resource resource;

    public MovieService(ChatClient.Builder chatClientBuilder){
        this.chatClient = chatClientBuilder.build();
    }

    public String movieInfo(String movieName){

        return Objects.requireNonNull(chatClient.prompt()
                        .user(userSpec -> userSpec.text(resource)
                                .param("movieName", movieName))
                        .call()
                        .chatResponse())
                .getResult()
                .getOutput().getText();

    }
}
